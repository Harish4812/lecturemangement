package com.example.LectureManagement.repository;

import com.example.LectureManagement.adapter.api.dto.request.SlideRequest;
import com.example.LectureManagement.adapter.api.dto.request.TeacherRequest;
import com.example.LectureManagement.adapter.api.dto.response.SlideResponse;
import com.example.LectureManagement.adapter.api.dto.response.TeacherResponse;
import com.example.LectureManagement.core.entity.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SlideRepository {
    List<SlideResponse> getAllSlides();
    SlideResponse getSlideById(UUID slideId);
    Boolean createSlide(SlideRequest slideRequest);
    Boolean updateSlide(SlideRequest slideRequest);
    Boolean deleteSlide(UUID slideId);
}

