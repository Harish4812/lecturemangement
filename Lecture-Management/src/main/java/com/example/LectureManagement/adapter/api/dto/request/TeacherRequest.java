package com.example.LectureManagement.adapter.api.dto.request;

import com.example.LectureManagement.adapter.api.dto.response.TeacherResponse;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TeacherRequest {
    UUID teacherId;
    String name;
    String email;
    String phoneNumber;
    Integer salary;
    List<TeacherResponse.Lecture> lectures;

    @Data
    @Builder
    public static class Lecture {
        UUID lectureId;
        String title;
        String description;
        ZonedDateTime dateOfLecture;
        String location;
        int durationMinutes;
    }
}
