package com.example.LectureManagement.adapter.dal;

import com.example.LectureManagement.adapter.api.dto.request.SlideRequest;
import com.example.LectureManagement.adapter.api.dto.response.SlideResponse;
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

    public List<SlideResponse> getAllSlides(){
        return null;
    }

    public SlideResponse getSlideById(UUID slideId){
        return null;
    }

    public Boolean createSlide(SlideRequest slideRequest){
        return null;
    }

    public Boolean updateSlide(SlideRequest slideRequest){
        return null;
    }

    public Boolean deleteSlide(UUID slideId){
        return null;
    }
}
