package com.example.LectureManagement.adapter.api.dto.response;

import com.example.LectureManagement.core.entity.model.Lecture;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class LectureResponse {
    UUID lectureId;
    String title;
    String description;
    String location;
    int durationMinutes;
    List<Slide> slides;
    List<Teacher> teachers;

    @Data
    @Builder
    static public class Teacher{
        UUID teacherId;
        String name;
        String email;
        String phoneNumber;
        Integer salary;
    }

    @Data
    @Builder
    static public class Slide{
        UUID slideId;
        String title;
        String content;
        int orderIndex;
    }

    public static LectureResponse.Teacher of(com.example.LectureManagement.core.entity.model.Teacher teacher){
        if(teacher == null)return null;
        return Teacher
                .builder()
                .teacherId(teacher.getTeacherId())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .phoneNumber(teacher.getPhoneNumber())
                .salary(teacher.getSalary())
                .build();
    }

    public static LectureResponse.Slide of(com.example.LectureManagement.core.entity.model.Slide slide){
        if(slide == null)return null;
        return Slide
                .builder()
                .slideId(slide.getSlideId())
                .title(slide.getTitle())
                .content(slide.getContent())
                .orderIndex(slide.getOrderIndex())
                .build();
    }

    public static LectureResponse of(Lecture lecture){
        return LectureResponse
                .builder()
                .lectureId(lecture.getLectureId())
                .title(lecture.getTitle())
                .description(lecture.getDescription())
                .location(lecture.getLocation())
                .slides(lecture.getSlides() == null ? null
                        : lecture
                        .getSlides()
                        .stream()
                        .map(LectureResponse::of)
                        .toList())
                .teachers(lecture.getTeachers() == null ? null
                        : lecture
                        .getTeachers()
                        .stream()
                        .map(LectureResponse::of)
                        .toList())
                .durationMinutes(lecture.getDurationMinutes())
                .build();
    }
}
