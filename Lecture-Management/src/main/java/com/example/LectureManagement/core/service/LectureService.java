package com.example.LectureManagement.core.service;

import com.example.LectureManagement.adapter.api.dto.request.LectureRequest;
import com.example.LectureManagement.adapter.api.dto.response.LectureResponse;
import com.example.LectureManagement.adapter.dal.LectureDal;
import com.example.LectureManagement.repository.LectureRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LectureService implements LectureRepository {

    @NonNull private final LectureDal lectureDal;

    public List<LectureResponse> getAllLectures() {
        return lectureDal.getAllLectures();
    }

    public LectureResponse getLectureById(UUID lectureId) {
        return lectureDal.getLectureById(lectureId);
    }

    public Boolean createLecture(LectureRequest lectureRequest) {
        return lectureDal.createLecture(lectureRequest);
    }

    public Boolean updateLecture(LectureRequest lectureRequest) {
        return lectureDal.updateLecture(lectureRequest);
    }

    public Boolean deleteLecture(UUID lectureId) {
        return lectureDal.deleteLecture(lectureId);
    }

}