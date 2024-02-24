package com.example.LectureManagement.adapter.api.dto.response;

import com.example.LectureManagement.core.entity.model.Slide;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SlideResponse {
    UUID slideId;
    String title;
    String content;
    int orderIndex;

    public static SlideResponse of(Slide slide){
        return SlideResponse
                .builder()
                .slideId(slide.getSlideId())
                .title(slide.getTitle())
                .content(slide.getContent())
                .orderIndex(slide.getOrderIndex())
                .build();
    }
}
