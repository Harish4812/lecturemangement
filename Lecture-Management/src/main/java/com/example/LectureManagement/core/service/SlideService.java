package com.example.LectureManagement.core.service;

import com.example.LectureManagement.adapter.api.dto.request.SlideRequest;
import com.example.LectureManagement.adapter.api.dto.response.SlideResponse;
import com.example.LectureManagement.adapter.dal.SlideDal;
import com.example.LectureManagement.repository.SlideRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SlideService implements SlideRepository {

    @NonNull private final SlideDal slideDal;

    public List<SlideResponse> getAllSlides(){
        return slideDal.getAllSlides();
    }

    public SlideResponse getSlideById(UUID slideId){
        return slideDal.getSlideById(slideId);
    }

    public Boolean createSlide(SlideRequest slideRequest) {
        return slideDal.createSlide(slideRequest);
    }

    public Boolean updateSlide(SlideRequest slideRequest) {
        return slideDal.updateSlide(slideRequest);
    }

    public Boolean deleteSlide(UUID slideId) {
        return slideDal.deleteSlide(slideId);
    }

}
