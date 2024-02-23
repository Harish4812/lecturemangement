package com.example.LectureManagement.adapter.dal;

import com.example.LectureManagement.adapter.api.dto.request.TeacherRequest;
import com.example.LectureManagement.adapter.api.dto.response.TeacherResponse;
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
public class TeacherDal {
    @NonNull
    private final Jdbi jdbi;

    public List<TeacherResponse> getAllTeachers(){
        return null;
    }

    public TeacherResponse getTeacherById(UUID teacherId){
        return null;
    }

    public Boolean createTeacher(TeacherRequest teacherRequest){
        return null;
    }

    public Boolean updateTeacher(TeacherRequest teacherRequest){
        return null;
    }

    public Boolean deleteTeacher(UUID teacherId){
        return null;
    }

}
