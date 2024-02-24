package com.example.LectureManagement.adapter.dal;

import com.example.LectureManagement.adapter.api.dto.request.SlideRequest;
import com.example.LectureManagement.adapter.api.dto.response.SlideResponse;
import com.example.LectureManagement.core.entity.model.Slide;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlideDal {
    @NonNull
    private final Jdbi jdbi;

    public List<SlideResponse> getAllSlides() {
        List<Slide> slides = jdbi.withHandle(handle -> handle.createQuery(
                                """
                                        SELECT 
                                           id AS slide_id,
                                           title AS title,
                                           content AS content,
                                           corder_index AS order_index
                                        FROM slide  
                                        """
                        )
                        .mapTo(Slide.class)
                        .stream()
                        .toList()
        );
        return slides
                .stream()
                .map(SlideResponse::of)
                .toList();
    }

    public SlideResponse getSlideById(UUID slideId) {
        Slide slide = jdbi.withHandle(handle -> handle.createQuery(
                                """
                                        SELECT
                                           id AS slide_id,
                                           title AS title,
                                           content AS content,
                                           corder_index AS order_index
                                        FROM slide  
                                        WHERE id = :id::uuid
                                        """
                        )
                        .bind("id", slideId)
                        .mapTo(Slide.class)
                        .findOne()
                        .orElse(null)
        );
        return slide == null ? null : SlideResponse.of(slide);
    }

    public Boolean createSlide(SlideRequest slideRequest) {
        jdbi.withHandle(handle -> handle.createUpdate(
                                """
                                        INSERT INTO slide
                                        (
                                           id,
                                           title,
                                           content,
                                           order_index,
                                           created_at,
                                           updated_at,
                                           updated_by
                                        )
                                        VALUES
                                        (
                                           :id::uuid,
                                           :title,
                                           :content,
                                           :order_index,
                                           now()::timestamptz,
                                           now()::timestamptz,
                                           (SELECT id FROM users LIMIT 1)
                                        )
                                        """
                        )
                        .bind("id", slideRequest.getSlideId())
                        .bind("title", slideRequest.getTitle())
                        .bind("content", slideRequest.getContent())
                        .bind("order_index", slideRequest.getOrderIndex())
                        .execute()
        );
        return true;
    }

    public Boolean updateSlide(SlideRequest slideRequest) {
        jdbi.withHandle(handle -> handle.createUpdate(
                                """
                                        INSERT INTO slide
                                        (
                                           id,
                                           title,
                                           content,
                                           order_index,
                                           created_at,
                                           updated_at,
                                           updated_by
                                        )
                                        VALUES
                                        (
                                           :id::uuid,
                                           :title,
                                           :content,
                                           :order_index,
                                           now()::timestamptz,
                                           now()::timestamptz,
                                           (SELECT id FROM users LIMIT 1)
                                        )
                                        ON CONFLICT (id)
                                        DO UPDATE
                                        SET
                                          id = EXCLUDED.id,
                                          title = EXCLUDED.title,
                                          content = EXCLUDED.content,
                                          order_index = EXCLUDED.order_index,
                                          updated_at = EXCLUDED.updated_at
                                        """
                        )
                        .bind("id", slideRequest.getSlideId())
                        .bind("title", slideRequest.getTitle())
                        .bind("content", slideRequest.getContent())
                        .bind("order_index", slideRequest.getOrderIndex())
                        .execute()
        );
        return true;
    }

    public Boolean deleteSlide(UUID slideId) {
        jdbi.withHandle(handle -> handle.createUpdate(
                """
                        DELETE FROM slide
                        WHERE id = :id::uuid
                        """
                )
                .bind("id", slideId)
                .execute()
        );
        return true;
    }
}
