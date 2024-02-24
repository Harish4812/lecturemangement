package com.example.LectureManagement.adapter.api.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SlideRequest {
    UUID slideId;
    String title;
    String content;
    int orderIndex;
}
