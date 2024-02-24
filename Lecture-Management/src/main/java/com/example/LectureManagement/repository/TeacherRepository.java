package com.example.LectureManagement.repository;

import com.example.LectureManagement.adapter.api.dto.request.TeacherRequest;
import com.example.LectureManagement.adapter.api.dto.response.TeacherResponse;
import com.example.LectureManagement.core.entity.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository{
    List<TeacherResponse> getAllTeachers();
    TeacherResponse getTeacherById(UUID teacherId);
    Boolean createTeacher(TeacherRequest teacherRequest);
    Boolean updateTeacher(TeacherRequest teacherRequest);
    Boolean deleteTeacher(UUID teacherId);
}
