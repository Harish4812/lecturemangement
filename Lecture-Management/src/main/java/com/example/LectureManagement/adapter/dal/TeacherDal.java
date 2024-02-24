package com.example.LectureManagement.adapter.dal;

import com.example.LectureManagement.adapter.api.dto.request.TeacherRequest;
import com.example.LectureManagement.adapter.api.dto.response.TeacherResponse;
import com.example.LectureManagement.core.entity.model.Lecture;
import com.example.LectureManagement.core.entity.model.Teacher;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeacherDal {
    @NonNull
    private final Jdbi jdbi;

    private static PreparedBatch lectureInsertionBatch() {
        Handle handle = null;
        return handle
                .prepareBatch("""
                        INSERT INTO teacher_lectures
                        (
                          teacher_id,
                          lectures_id
                        )
                        VALUES
                        (
                          :teacher_id,
                          :lectures_id
                        )
                        """);
    }

    public List<TeacherResponse> getAllTeachers() {
        List<Teacher> teachers = jdbi.withHandle(handle -> handle.createQuery(
                                """
                                        SELECT 
                                          id AS teacher_id,
                                          name AS name,
                                          emial AS email,
                                          phone_number AS phone_number,
                                          salary AS salary
                                        """
                        )
                        .mapTo(Teacher.class)
                        .list()
        );
        for (Teacher teacher : teachers) {
            teacher.setLectures(getLectures(teacher.getTeacherId()));
        }
        return teachers
                .stream()
                .map(TeacherResponse::of)
                .toList();
    }

    public TeacherResponse getTeacherById(UUID teacherId) {
        Teacher teacher = jdbi.withHandle(handle -> handle.createQuery(
                                """
                                        SELECT 
                                          id AS teacher_id,
                                          name AS name,
                                          emial AS email,
                                          phone_number AS phone_number,
                                          salary AS salary
                                          WHERE id = :id::uuid
                                        """
                        )
                        .bind("id", teacherId)
                        .mapTo(Teacher.class)
                        .findOne()
                        .orElse(null)
        );
        if (teacher != null) {
            teacher.setLectures(getLectures(teacher.getTeacherId()));
        }
        return teacher == null? null: TeacherResponse.of(teacher);
    }

    public Boolean createTeacher(TeacherRequest teacherRequest) {
        jdbi.withHandle(handle -> handle.createUpdate(
                                """
                                        INSERT INTO teacher
                                        (
                                           id,
                                           name,
                                           email,
                                           phone_number,
                                           salary,
                                           created_at,
                                           updated_at,
                                           updated_by
                                        )
                                        VALUES
                                        (
                                          :id,
                                          :name,
                                          :email,
                                          :phone_number,
                                          :salary,
                                          now(),
                                          now(),
                                          (SELECT id FROM users LIMIT 1)
                                        )
                                        """
                        )
                        .bind("id", teacherRequest.getTeacherId())
                        .bind("name", teacherRequest.getName())
                        .bind("phone_number", teacherRequest.getPhoneNumber())
                        .bind("salary", teacherRequest.getSalary())
                        .execute()
        );
        var lectureInsertionBatch = lectureInsertionBatch();
        for (TeacherResponse.Lecture lecture : teacherRequest.getLectures()) {
            lectureInsertionBatch
                    .bind("lecture_id", lecture.getLectureId())
                    .bind("teachers_id", teacherRequest.getTeacherId())
                    .add();
        }
        lectureInsertionBatch.execute();
        return true;
    }

    public Boolean updateTeacher(TeacherRequest teacherRequest) {

        jdbi.withHandle(handle -> handle.createUpdate(
                                """
                                        DELETE FROM teacher
                                        WHERE id = :id::uuid
                                        """
                        )
                        .bind("id", teacherRequest.getTeacherId())
                        .execute()
        );

        jdbi.withHandle(handle -> handle.createUpdate(
                                """
                                        INSERT INTO teacher
                                        (
                                           id,
                                           name,
                                           email,
                                           phone_number,
                                           salary,
                                           created_at,
                                           updated_at,
                                           updated_by
                                        )
                                        VALUES
                                        (
                                          :id,
                                          :name,
                                          :email,
                                          :phone_number,
                                          :salary,
                                          now(),
                                          now(),
                                          (SELECT id FROM users LIMIT 1)
                                        )
                                        ON CONFLICT
                                        DO UPDATE
                                        SET
                                           id = EXCLUDED.id,
                                           name = EXCLUDED.name,
                                           email = EXCLUDED.email,
                                           phone_number = EXCLUDED.phone_number,
                                           salary = EXCLUDED.salary
                                        """
                        )
                        .bind("id", teacherRequest.getTeacherId())
                        .bind("name", teacherRequest.getName())
                        .bind("phone_number", teacherRequest.getPhoneNumber())
                        .bind("salary", teacherRequest.getSalary())
                        .execute()
        );
        var lectureInsertionBatch = lectureInsertionBatch();
        for (TeacherResponse.Lecture lecture : teacherRequest.getLectures()) {
            lectureInsertionBatch
                    .bind("lecture_id", lecture.getLectureId())
                    .bind("teachers_id", teacherRequest.getTeacherId())
                    .add();
        }
        lectureInsertionBatch.execute();
        return true;
    }

    public Boolean deleteTeacher(UUID teacherId) {
        jdbi.withHandle(handle -> handle.createUpdate(
                                """
                                        DELETE FROM teacher
                                        WHERE id = :id::uuid
                                        """
                        )
                        .bind("id", teacherId)
                        .execute()
        );
        return true;
    }

    public List<Lecture> getLectures(UUID teacherId){
        List<UUID> lecturesId = jdbi.withHandle(handle -> handle.createQuery(
                """
                        SELECT lecture_id
                        FROM teacher_lectures
                        WHERE teachers_id = :id::uuid
                        """
                )
                .bind("id", teacherId)
                .mapTo(UUID.class)
                .list()
        );

        return jdbi.withHandle(handle -> handle.createQuery(
                """
                        SELECT 
                           id AS lecture_id,
                           title AS title,
                           description AS description,
                           location AS location,
                           duration_minutes AS duration_minutes
                        FROM lecture
                        WHERE id in (<lectures_ids>)   
                        """
                )
                .bindList("lecture_ids", lecturesId)
                .mapTo(Lecture.class)
                .stream()
                .toList()
        );
    }

}
