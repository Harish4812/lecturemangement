//package com.example.LectureManagement.core.service;
//
//import com.example.LectureManagement.adapter.api.dto.response.LectureResponse;
//import com.example.LectureManagement.core.entity.model.Lecture;
//import com.example.LectureManagement.repository.LectureRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.dao.EmptyResultDataAccessException;
//
//import java.time.ZonedDateTime;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class LectureServiceTest {
//
//    @Mock
//    private LectureRepository lectureRepository;
//
//    @InjectMocks
//    private LectureService lectureService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testGetAllLectures() {
//        List<Lecture> expectedLectures = Arrays.asList(
//                Lecture.builder()
//                        .lectureId(UUID.randomUUID())
//                        .title("Title 1")
//                        .description("Description 1")
//                        .dateOfLecture(ZonedDateTime.now())
//                        .location("Auditorium A")
//                        .durationMinutes(60)
//                        .build(),
//                Lecture.builder()
//                        .lectureId(UUID.randomUUID())
//                        .title("Title 2")
//                        .description("Description 2")
//                        .dateOfLecture(ZonedDateTime.now())
//                        .location("Auditorium A")
//                        .durationMinutes(90)
//                        .build()
//        );
//        when(lectureRepository.getAllLectures()).thenReturn(expectedLectures);
//        List<LectureResponse> actualLectures = lectureService.getAllLectures();
//        assertEquals(expectedLectures.size(), actualLectures.size());
//        assertEquals(expectedLectures, actualLectures);
//    }
//
//    @Test
//    void testGetLectureById_WithValidId_ReturnsLecture() {
//        UUID id = UUID.randomUUID();
//        Lecture expectedLecture = Lecture.builder()
//                .id(id)
//                .title("Title")
//                .description("Description")
//                .dateOfLecture(ZonedDateTime.now())
//                .location("Auditorium A")
//                .durationMinutes(60)
//                .build();
//        when(lectureRepository.findById(id)).thenReturn(Optional.of(expectedLecture));
//        Optional<Lecture> actualLecture = lectureService.getLectureById(id);
//        assertTrue(actualLecture.isPresent());
//        assertEquals(expectedLecture, actualLecture.get());
//    }
//
//    @Test
//    void testGetLectureById_WithInvalidId_ReturnsEmpty() {
//        UUID id = UUID.randomUUID();
//        when(lectureRepository.findById(id)).thenReturn(Optional.empty());
//        Optional<Lecture> actualLecture = lectureService.getLectureById(id);
//        assertFalse(actualLecture.isPresent());
//    }
//
//    @Test
//    void testCreateLecture_WithValidLecture_ReturnsCreatedLecture() {
//        Lecture inputLecture = Lecture.builder()
//                .title("Title")
//                .description("Description")
//                .dateOfLecture(ZonedDateTime.now())
//                .location("Auditorium A")
//                .durationMinutes(60)
//                .build();
//        when(lectureRepository.save(inputLecture)).thenReturn(inputLecture);
//        Lecture createdLecture = lectureService.createLecture(inputLecture);
//        assertEquals(inputLecture, createdLecture);
//    }
//
//    @Test
//    void testCreateLecture_WithNullLecture_ReturnsNull() {
//        Lecture createdLecture = lectureService.createLecture(null);
//        assertNull(createdLecture);
//    }
//
//    @Test
//    void testUpdateLecture_WithValidIdAndLectureDetails_ReturnsUpdatedLecture() {
//        UUID id = UUID.randomUUID();
//        Lecture existingLecture = Lecture.builder()
//                .id(id)
//                .title("Title")
//                .description("Description")
//                .dateOfLecture(ZonedDateTime.now())
//                .location("Auditorium A")
//                .durationMinutes(60)
//                .build();
//        Lecture updatedLecture = Lecture.builder()
//                .id(id)
//                .title("Updated Title")
//                .description("Updated Description")
//                .dateOfLecture(ZonedDateTime.now())
//                .location("Auditorium A")
//                .durationMinutes(60)
//                .build();
//        when(lectureRepository.findById(id)).thenReturn(Optional.of(existingLecture));
//        when(lectureRepository.save(existingLecture)).thenReturn(updatedLecture);
//        Lecture returnedLecture = lectureService.updateLecture(id, updatedLecture);
//        assertEquals(updatedLecture, returnedLecture);
//    }
//
//    @Test
//    void testUpdateLecture_WithInvalidId_ReturnsNull() {
//        UUID id = UUID.randomUUID();
//        Lecture updatedLecture = Lecture.builder()
//                .id(UUID.randomUUID())
//                .title("Title")
//                .description("Description")
//                .dateOfLecture(ZonedDateTime.now())
//                .location("Auditorium A")
//                .durationMinutes(60)
//                .build();
//        when(lectureRepository.findById(id)).thenReturn(Optional.empty());
//        Lecture returnedLecture = lectureService.updateLecture(id, updatedLecture);
//        assertNull(returnedLecture);
//    }
//
//    @Test
//    void testUpdateLecture_WithNullLectureDetails_ReturnsNull() {
//        UUID id = UUID.randomUUID();
//        Lecture returnedLecture = lectureService.updateLecture(id, null);
//        assertNull(returnedLecture);
//    }
//
//    @Test
//    void testDeleteLecture_WithValidId_DeletesLecture() {
//        UUID id = UUID.randomUUID();
//        assertDoesNotThrow(() -> lectureService.deleteLecture(id));
//        verify(lectureRepository, times(1)).deleteById(id);
//    }
//
//    @Test
//    void testDeleteLecture_WithInvalidId_DoesNotThrowException() {
//        UUID id = UUID.randomUUID();
//        doThrow(EmptyResultDataAccessException.class).when(lectureRepository).deleteById(id);
//        Throwable exception = assertThrows(EmptyResultDataAccessException.class, () -> lectureService.deleteLecture(id));
//        verify(lectureRepository, times(1)).deleteById(id);
//    }
//
//
//}
