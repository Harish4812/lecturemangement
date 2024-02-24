package com.example.LectureManagement.core.entity.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Slide {
    UUID slideId;
    String title;
    String content;
    int orderIndex;
}
