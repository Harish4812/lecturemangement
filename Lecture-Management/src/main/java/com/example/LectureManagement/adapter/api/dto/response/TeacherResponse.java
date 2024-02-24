package com.example.LectureManagement.adapter.api.dto.response;

import com.example.LectureManagement.core.entity.model.Lecture;
import com.example.LectureManagement.core.entity.model.Teacher;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TeacherResponse {
    UUID teacherId;
    String name;
    String email;
    String phoneNumber;
    Integer salary;
    List<Lecture> lectures;

    @Data
    @Builder
    public static class Lecture {
        UUID lectureId;
        String title;
        String description;
        String location;
        int durationMinutes;
    }

    public static TeacherResponse.Lecture of(com.example.LectureManagement.core.entity.model.Lecture lecture){
        return Lecture
                .builder()
                .lectureId(lecture.getLectureId())
                .title(lecture.getTitle())
                .description(lecture.getDescription())
                .location(lecture.getLocation())
                .durationMinutes(lecture.getDurationMinutes())
                .build();
    }

    public static TeacherResponse of(Teacher teacher){
        return TeacherResponse
                .builder()
                .teacherId(teacher.getTeacherId())
                .email(teacher.getEmail())
                .phoneNumber(teacher.getPhoneNumber())
                .salary(teacher.getSalary())
                .lectures(teacher.getLectures() == null ? null
                        :teacher.getLectures()
                        .stream()
                        .map(TeacherResponse::of)
                        .toList())
                .build();
    }

}
