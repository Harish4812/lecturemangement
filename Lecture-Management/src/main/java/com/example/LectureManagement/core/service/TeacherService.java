package com.example.LectureManagement.core.service;

import com.example.LectureManagement.adapter.api.dto.request.TeacherRequest;
import com.example.LectureManagement.adapter.api.dto.response.TeacherResponse;
import com.example.LectureManagement.adapter.dal.TeacherDal;
import com.example.LectureManagement.core.entity.model.Teacher;
import com.example.LectureManagement.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeacherService implements TeacherRepository{

    @NonNull private final TeacherDal teacherDal;

    public List<TeacherResponse> getAllTeachers() {
        return teacherDal.getAllTeachers();
    }

    public TeacherResponse getTeacherById(UUID teacherId) {
        return teacherDal.getTeacherById(teacherId);
    }

    public Boolean createTeacher(TeacherRequest teacherRequest) {
        return teacherDal.createTeacher(teacherRequest);
    }

    public Boolean updateTeacher(TeacherRequest teacherRequest) {
        return teacherDal.updateTeacher(teacherRequest);
    }

    public Boolean deleteTeacher(UUID id) {
       return teacherDal.deleteTeacher(id);
    }

}
