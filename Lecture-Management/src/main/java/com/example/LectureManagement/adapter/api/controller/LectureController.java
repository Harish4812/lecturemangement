package com.example.LectureManagement.adapter.api.controller;

import com.example.LectureManagement.adapter.api.dto.request.LectureRequest;
import com.example.LectureManagement.adapter.api.dto.response.LectureResponse;
import com.example.LectureManagement.adapter.exception.ExceptionHandle;
import com.example.LectureManagement.core.service.LectureService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    @NonNull private final LectureService lectureService;

    @NonNull private final ExceptionHandle exceptionHandle;

    @GetMapping("/")
    public ResponseEntity<List<LectureResponse>> getAllLectures() {
      return ResponseEntity.ok(lectureService.getAllLectures());
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity<LectureResponse> getLectureById(@PathVariable("lectureId") UUID lectureId) {
       return ResponseEntity.ok(lectureService.getLectureById(lectureId));
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> createLecture(@RequestBody LectureRequest lectureRequest) {
        return ResponseEntity.ok(lectureService.createLecture(lectureRequest));
    }

    @PutMapping("/{lectureId}")
    public ResponseEntity<Boolean> updateLecture(@RequestBody LectureRequest lectureRequest) {
        return ResponseEntity.ok(lectureService.updateLecture(lectureRequest));
    }

    @DeleteMapping("/{lectureId}")
    public ResponseEntity<Boolean> deleteLecture(@PathVariable("lectureId") UUID lectureId) {
       return ResponseEntity.ok(lectureService.deleteLecture(lectureId));
    }

}
