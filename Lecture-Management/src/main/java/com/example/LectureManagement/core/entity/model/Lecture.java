package com.example.LectureManagement.core.entity.model;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Lecture {
    UUID lectureId;
    String title;
    String description;
    ZonedDateTime dateOfLecture;
    String location;
    int durationMinutes;
    List<Slide> slides;
    List<Teacher> teachers;
}
