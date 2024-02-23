package com.example.LectureManagement.repository;

import com.example.LectureManagement.adapter.api.dto.request.LectureRequest;
import com.example.LectureManagement.adapter.api.dto.response.LectureResponse;

import java.util.List;
import java.util.UUID;

public interface LectureRepository {
    List<LectureResponse> getAllLectures();
    LectureResponse getLectureById(UUID lectureId);
    Boolean createLecture(LectureRequest lectureRequest);
    Boolean updateLecture(LectureRequest lectureRequest);
    Boolean deleteLecture(UUID lectureId);
}
