package com.example.LectureManagement.adapter.api.dto.request;


import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Data
@Builder
public class LectureRequest {
    UUID lectureId;
    String title;
    String description;
    String location;
    int durationMinutes;
    List<com.example.LectureManagement.adapter.api.dto.response.LectureResponse.Slide> slides;
    List<com.example.LectureManagement.adapter.api.dto.response.LectureResponse.Teacher> teachers;

    @Data
    @Builder
    static public class Teacher {
        UUID teacherId;
        String name;
        String email;
        String phoneNumber;
        Integer salary;
    }

    @Data
    @Builder
    static public class Slide {
        UUID slideId;
        String title;
        String content;
        int orderIndex;
    }

}