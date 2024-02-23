package com.example.LectureManagement.core.entity.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Teacher {
    UUID teacherId;
    String name;
    String email;
    String phoneNumber;
    Integer salary;
    List<Lecture> lectures;
}

