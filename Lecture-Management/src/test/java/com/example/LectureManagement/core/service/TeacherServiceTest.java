//package com.example.LectureManagement.core.service;
//
//import com.example.LectureManagement.core.entity.model.Teacher;
//import com.example.LectureManagement.repository.TeacherRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class TeacherServiceTest {
//
//    @Mock
//    private TeacherRepository teacherRepository;
//
//    @InjectMocks
//    private TeacherService teacherService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void getAllTeachers_ReturnsAllTeachers() {
//        List<Teacher> expectedTeachers = Arrays.asList(
//                Teacher.builder().id(UUID.randomUUID()).name("Teacher 1").email("email1@example.com").phoneNumber("1234567890").salary(50000).build(),
//                Teacher.builder().id(UUID.randomUUID()).name("Teacher 2").email("email2@example.com").phoneNumber("9876543210").salary(60000).build()
//        );
//        when(teacherRepository.findAll()).thenReturn(expectedTeachers);
//        List<Teacher> actualTeachers = teacherService.getAllTeachers();
//        assertEquals(expectedTeachers.size(), actualTeachers.size());
//        assertEquals(expectedTeachers, actualTeachers);
//    }
//
//    @Test
//    void getTeacherById_WithValidId_ReturnsTeacher() {
//        UUID id = UUID.randomUUID();
//        Teacher expectedTeacher = Teacher.builder().id(id).name("Teacher").email("email@example.com").phoneNumber("1234567890").salary(50000).build();
//        when(teacherRepository.findById(id)).thenReturn(Optional.of(expectedTeacher));
//        Optional<Teacher> actualTeacher = teacherService.getTeacherById(id);
//        assertTrue(actualTeacher.isPresent());
//        assertEquals(expectedTeacher, actualTeacher.get());
//    }
//
//    @Test
//    void getTeacherById_WithInvalidId_ReturnsEmpty() {
//        UUID id = UUID.randomUUID();
//        when(teacherRepository.findById(id)).thenReturn(Optional.empty());
//        Optional<Teacher> actualTeacher = teacherService.getTeacherById(id);
//        assertFalse(actualTeacher.isPresent());
//    }
//
//    @Test
//    void createTeacher_ReturnsCreatedTeacher() {
//        Teacher teacherToCreate = Teacher.builder().name("New Teacher").email("newteacher@example.com").phoneNumber("9876543210").salary(60000).build();
//        Teacher createdTeacher = Teacher.builder().id(UUID.randomUUID()).name("New Teacher").email("newteacher@example.com").phoneNumber("9876543210").salary(60000).build();
//        when(teacherRepository.save(teacherToCreate)).thenReturn(createdTeacher);
//        Teacher actualTeacher = teacherService.createTeacher(teacherToCreate);
//        assertEquals(createdTeacher, actualTeacher);
//    }
//
//    @Test
//    void updateTeacher_WithValidId_ReturnsUpdatedTeacher() {
//        UUID id = UUID.randomUUID();
//        Teacher existingTeacher = Teacher.builder().id(id).name("Teacher").email("email@example.com").phoneNumber("1234567890").salary(50000).build();
//        Teacher updatedTeacher = Teacher.builder().id(id).name("Updated Teacher").email("updatedemail@example.com").phoneNumber("9876543210").salary(60000).build();
//        when(teacherRepository.findById(id)).thenReturn(Optional.of(existingTeacher));
//        when(teacherRepository.save(existingTeacher)).thenReturn(updatedTeacher);
//        Teacher actualTeacher = teacherService.updateTeacher(id, updatedTeacher);
//        assertEquals(updatedTeacher, actualTeacher);
//    }
//
//    @Test
//    void updateTeacher_WithInvalidId_ReturnsNull() {
//        UUID id = UUID.randomUUID();
//        Teacher updatedTeacher = Teacher.builder().id(id).name("Updated Teacher").email("updatedemail@example.com").phoneNumber("9876543210").salary(60000).build();
//        when(teacherRepository.findById(id)).thenReturn(Optional.empty());
//        Teacher actualTeacher = teacherService.updateTeacher(id, updatedTeacher);
//        assertNull(actualTeacher);
//    }
//
//    @Test
//    void deleteTeacher_WithValidId_DeletesTeacher() {
//        UUID id = UUID.randomUUID();
//        teacherService.deleteTeacher(id);
//        verify(teacherRepository, times(1)).deleteById(id);
//    }
//
//    @Test
//    void deleteTeacher_WithInvalidId_DoesNothing() {
//        UUID id = UUID.randomUUID();
//        teacherService.deleteTeacher(id);
//        verify(teacherRepository, times(1)).deleteById(id);
//    }
//}
