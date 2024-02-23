package com.example.LectureManagement.adapter.api.controller;

import com.example.LectureManagement.adapter.api.dto.request.TeacherRequest;
import com.example.LectureManagement.adapter.api.dto.response.TeacherResponse;
import com.example.LectureManagement.adapter.exception.ExceptionHandle;
import com.example.LectureManagement.core.service.TeacherService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

    @NonNull  private final TeacherService teacherService;

    @NonNull ExceptionHandle exceptionHandle;

    @GetMapping("/")
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable("teacherId") UUID teacherId) {
        return ResponseEntity.ok(teacherService.getTeacherById(teacherId));
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> createTeacher(@RequestBody TeacherRequest teacherRequest) {
        return ResponseEntity.ok(teacherService.createTeacher(teacherRequest));
    }

    @PostMapping("/{teacherId}")
    public ResponseEntity<Boolean> updateTeacher(@RequestBody TeacherRequest teacherRequest) {
        return ResponseEntity.ok(teacherService.updateTeacher(teacherRequest));
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Boolean> deleteTeacher(@PathVariable("teacherId") UUID teacherId) {
        return ResponseEntity.ok(teacherService.deleteTeacher(teacherId));
    }

}

