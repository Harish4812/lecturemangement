package com.example.LectureManagement.adapter.api.controller;

import com.example.LectureManagement.adapter.api.dto.request.SlideRequest;
import com.example.LectureManagement.adapter.api.dto.response.SlideResponse;
import com.example.LectureManagement.adapter.exception.ExceptionHandle;
import com.example.LectureManagement.core.service.SlideService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/slides")
public class SlideController {

    @NonNull  private final SlideService slideService;

    @NonNull ExceptionHandle exceptionHandle;

    @GetMapping("/")
    public ResponseEntity<List<SlideResponse>> getAllSlides() {
        return ResponseEntity.ok(slideService.getAllSlides());
    }

    @GetMapping("/{slideId}")
    public ResponseEntity<SlideResponse> getSlideById(@PathVariable("slideId") UUID slideId) {
        return ResponseEntity.ok(slideService.getSlideById(slideId));
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> createSlide(@RequestBody SlideRequest slideRequest) {
        return ResponseEntity.ok(slideService.createSlide(slideRequest));
    }

    @PostMapping("/{slideId}")
    public ResponseEntity<Boolean> updateSlide(@RequestBody SlideRequest slideRequest) {
       return ResponseEntity.ok(slideService.updateSlide(slideRequest));
    }

    @DeleteMapping("/{slideId}")
    public ResponseEntity<Boolean> deleteSlide(@PathVariable("slideId") UUID slideId) {
        return ResponseEntity.ok(slideService.deleteSlide(slideId));
    }

}
