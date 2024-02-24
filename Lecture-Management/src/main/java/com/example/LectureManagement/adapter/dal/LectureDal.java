package com.example.LectureManagement.adapter.dal;

import com.example.LectureManagement.adapter.api.dto.request.LectureRequest;
import com.example.LectureManagement.adapter.api.dto.response.LectureResponse;
import com.example.LectureManagement.core.entity.model.Lecture;
import com.example.LectureManagement.core.entity.model.Slide;
import com.example.LectureManagement.core.entity.model.Teacher;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.JdbiException;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class LectureDal {

    @NonNull
    private final Jdbi jdbi;


    private static PreparedBatch lectureTeacherMappingInsertionBatch(Handle handle) {
        return handle.prepareBatch(
                """
                        INSERT INTO lecture_teachers
                        (
                           lecture_id,
                           teachers_id
                        )
                        VALUES
                        (
                           :lecture_id::UUID,
                           :teachers_id::UUID
                        )
                        """
        );
    }

    private static PreparedBatch lectureSlideMappingInsertionBatch(Handle handle) {
        return handle.prepareBatch(
                """
                        INSERT INTO lecture_slides
                        (
                           lecture_id,
                           slides_id
                        )
                        VALUES
                        (
                           :lecture_id::UUID,
                           :slides_id::UUID
                        )
                        """
        );
    }

    private static PreparedBatch teacherInsertionBatch(Handle handle) {
        return handle.prepareBatch(
                """
                        INSERT INTO teacher
                        (
                          id,
                          name,
                          email,
                          phone_number,
                          salary
                        )
                        VALUES
                        (
                          :id::uuid,
                          :name,
                          :email,
                          :phone_number,
                          :salary
                        )
                        """
        );
    }

    private static PreparedBatch slideInsertionBatch(Handle handle) {
        return handle.prepareBatch(
                """
                        INSERT INTO slide
                        (
                          id,
                          title,
                          content,
                          order_index
                        )
                        VALUES
                        (
                          :id::uuid,
                          :title,
                          :content,
                          :order_index
                        )
                        """
        );
    }


    public List<LectureResponse> getAllLectures() {
        List<Lecture> lectures = jdbi.withHandle(handle -> handle.createQuery(
                        """
                                SELECT
                                   l.id AS lecture_id,
                                   l.title AS title,
                                   l.description AS description,
                                   l.date_of_lecture AS date_of_lecture,
                                   l."location" AS "location",
                                   l.duration_minutes AS duration_minutes
                                FROM lecture l
                                """)
                .mapTo(Lecture.class)
                .stream()
                .toList()
        );

        for (Lecture lecture : lectures) {
            lecture.setTeachers(getAllTeachers(lecture.getLectureId()));
            lecture.setSlides(getALLSlides(lecture.getLectureId()));
        }
        return lectures
                .stream()
                .map(LectureResponse::of)
                .toList();
    }

    public LectureResponse getLectureById(UUID lectureId) {
        Lecture lecture = jdbi.withHandle(handle -> handle.createQuery(
                        """
                                SELECT
                                   l.id AS lecture_id,
                                   l.title AS title,
                                   l.description AS description,
                                   l.date_of_lecture AS date_of_lecture,
                                   l."location" AS "location",
                                   l.duration_minutes AS duration_minutes
                                FROM lecture l
                                WHERE l.id = :lecture_id::UUID
                                """)
                .bind("lecture_id", lectureId)
                .mapTo(Lecture.class)
                .findOne()
                .orElse(null)
        );
        if (lecture != null) {
            lecture.setTeachers(getAllTeachers(lectureId));
            lecture.setSlides(getALLSlides(lectureId));
        }
        return lecture == null ? null : LectureResponse.of(lecture);
    }

    public Boolean createLecture(LectureRequest lectureRequest) {
        try {
            return jdbi.inTransaction(handle -> {
                handle.createUpdate(
                                """
                                        INSERT INTO lecture
                                        (
                                           id,
                                           title,
                                           description,
                                           date_of_lecture,
                                           location,
                                           duration_minutes,
                                           created_at,
                                           updated_at,
                                           updated_by
                                        )
                                        VALUES
                                        (
                                          gen_random_uuid(),
                                          :title,
                                          :description,
                                          :now()::timestamptz,
                                          :location,
                                          :duration_minutes,
                                          :now()::timestamptz,
                                          :now()::timestamptz,
                                          (SELECT id FROM users LIMIT 1)
                                        )
                                        ON CONFLICT (id)
                                        DO UPDATE
                                        SET
                                          updated_at = EXCLUDED.updated_at
                                        """
                        )
                        .bind("title", lectureRequest.getTitle())
                        .bind("description", lectureRequest.getDescription())
                        .bind("location", lectureRequest.getLocation())
                        .bind("duration_minutes", lectureRequest.getDurationMinutes())
                        .execute();

                var lectureTeacherInsertionBatch = lectureTeacherMappingInsertionBatch(handle);
                var teacherInsertionBatch = teacherInsertionBatch(handle);

                for (LectureResponse.Teacher teacher : lectureRequest.getTeachers()) {
                    lectureTeacherInsertionBatch
                            .bind("lecture_id", lectureRequest.getLectureId())
                            .bind("teachers_id", teacher.getTeacherId())
                            .add();
                    teacherInsertionBatch
                            .bind("id", teacher.getTeacherId())
                            .bind("name", teacher.getName())
                            .bind("phone_number", teacher.getPhoneNumber())
                            .bind("salary", teacher.getSalary())
                            .bind("email", teacher.getEmail())
                            .add();
                }

                lectureTeacherInsertionBatch.execute();
                teacherInsertionBatch.execute();

                var slideTeacherInsertionBatch = lectureSlideMappingInsertionBatch(handle);
                var slideInsertionBatch = slideInsertionBatch(handle);

                for (LectureResponse.Slide slide : lectureRequest.getSlides()) {
                    slideTeacherInsertionBatch
                            .bind("lecture_id", lectureRequest.getLectureId())
                            .bind("slides_id", slide.getSlideId())
                            .add();
                    slideInsertionBatch
                            .bind("id", slide.getSlideId())
                            .bind("order_index", slide.getOrderIndex())
                            .bind("content", slide.getContent())
                            .bind("title", slide.getTitle())
                            .add();
                }

                slideTeacherInsertionBatch.execute();
                slideInsertionBatch.execute();

                return true;
            });
        } catch (JdbiException e) {
            log.error("Error while processing {}", e.getMessage());
            throw e;
        }
    }

    public Boolean updateLecture(LectureRequest lectureRequest) {
        try {
            return jdbi.inTransaction(handle -> {

                handle.createUpdate("""
                                DELETE FROM lecture
                                WHERE id = :id::UUID
                                """
                        )
                        .bind("id", lectureRequest.getLectureId())  //delete is on cascading so mapping slide_ids and teacher_ids will automatically deleted
                        .execute();

                handle.createUpdate(
                                """
                                        INSERT INTO lecture
                                        (
                                           id,
                                           title,
                                           description,
                                           date_of_lecture,
                                           location,
                                           duration_minutes,
                                           created_at,
                                           updated_at,
                                           updated_by
                                        )
                                        VALUES
                                        (
                                          gen_random_uuid(),
                                          :title,
                                          :description,
                                          :now()::timestamptz,
                                          :location,
                                          :duration_minutes,
                                          :now()::timestamptz,
                                          :now()::timestamptz,
                                          (SELECT id FROM users LIMIT 1)
                                        )
                                        ON CONFLICT (id)
                                        DO UPDATE
                                        SET
                                          updated_at = EXCLUDED.updated_at
                                        """
                        )
                        .bind("title", lectureRequest.getTitle())
                        .bind("description", lectureRequest.getDescription())
                        .bind("location", lectureRequest.getLocation())
                        .bind("duration_minutes", lectureRequest.getDurationMinutes())
                        .execute();

                var lectureTeacherInsertionBatch = lectureTeacherMappingInsertionBatch(handle);
                var teacherInsertionBatch = teacherInsertionBatch(handle);

                for (LectureResponse.Teacher teacher : lectureRequest.getTeachers()) {
                    lectureTeacherInsertionBatch
                            .bind("lecture_id", lectureRequest.getLectureId())
                            .bind("teachers_id", teacher.getTeacherId())
                            .add();
                    teacherInsertionBatch
                            .bind("id", teacher.getTeacherId())
                            .bind("name", teacher.getName())
                            .bind("phone_number", teacher.getPhoneNumber())
                            .bind("salary", teacher.getSalary())
                            .bind("email", teacher.getEmail())
                            .add();
                }

                lectureTeacherInsertionBatch.execute();
                teacherInsertionBatch.execute();

                var slideTeacherInsertionBatch = lectureSlideMappingInsertionBatch(handle);
                var slideInsertionBatch = slideInsertionBatch(handle);

                for (LectureResponse.Slide slide : lectureRequest.getSlides()) {
                    slideTeacherInsertionBatch
                            .bind("lecture_id", lectureRequest.getLectureId())
                            .bind("slides_id", slide.getSlideId())
                            .add();
                    slideInsertionBatch
                            .bind("id", slide.getSlideId())
                            .bind("order_index", slide.getOrderIndex())
                            .bind("content", slide.getContent())
                            .bind("title", slide.getTitle())
                            .add();
                }

                slideTeacherInsertionBatch.execute();
                slideInsertionBatch.execute();

                return true;
            });
        } catch (JdbiException e) {
            log.error("Error while processing {}", e.getMessage());
            throw e;
        }
    }

    public Boolean deleteLecture(UUID lectureId) {
        //delete is on cascading so the teacher_ids and slides_ids mapping will automatically deleted
        jdbi.withHandle(handle -> handle.createUpdate(
                                """
                                        DELETE FROM lecture
                                        WHERE id = :id::UUID
                                        """
                        )
                        .bind("id", lectureId)
                        .execute()
        );
        return true;
    }

    public List<Teacher> getAllTeachers(UUID lectureId) {
        List<UUID> teacherIds = getTeacherIdsFromLectureId(lectureId);
        if (teacherIds.isEmpty()) return null;
        return jdbi.withHandle(handle -> handle.createQuery(
                                """
                                        SELECT
                                            t.id AS teacher_id,
                                            t.name AS name,
                                            t.email AS email,
                                            t.phone_number AS phone_number,
                                            t.salary AS salary
                                          FROM teacher t
                                          WHERE t.id in (<teacher_ids>)
                                        """
                        )
                        .bindList("teacher_ids", teacherIds)
                        .mapTo(Teacher.class)
                        .list()
        );
    }

    List<UUID> getTeacherIdsFromLectureId(UUID lectureId) {
        return jdbi.withHandle(handle -> handle.createQuery(
                                """
                                        SELECT teachers_id
                                        FROM lecture_teachers
                                        WHERE lecture_id = :lecture_id::UUID
                                        """
                        )
                        .bind("lecture_id", lectureId)
                        .mapTo(UUID.class)
                        .list()
        );
    }

    public List<Slide> getALLSlides(UUID lectureId) {
        List<UUID> slideIds = getSlideIdsFromLectureId(lectureId);
        if (slideIds.isEmpty()) return null;
        return jdbi.withHandle(handle -> handle.createQuery(
                                """
                                        SELECT
                                          s.id AS slide_id,
                                          s.title AS title,
                                          s."content" AS "content",
                                          s.order_index AS order_index
                                        FROM slide s
                                        WHERE s.id in (<slide_ids>)
                                        """
                        )
                        .bindList("slide_ids", slideIds)
                        .mapTo(Slide.class)
                        .list()
        );
    }

    List<UUID> getSlideIdsFromLectureId(UUID lectureId) {
        return jdbi.withHandle(handle -> handle.createQuery(
                                """
                                        SELECT slides_id
                                        FROM lecture_slides
                                        WHERE lecture_id = :lecture_id::UUID
                                        """
                        )
                        .bind("lecture_id", lectureId)
                        .mapTo(UUID.class)
                        .list()
        );
    }

}
