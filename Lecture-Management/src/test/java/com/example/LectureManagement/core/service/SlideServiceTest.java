//package com.example.LectureManagement.core.service;
//
//import com.example.LectureManagement.core.entity.model.Slide;
//import com.example.LectureManagement.repository.SlideRepository;
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
//class SlideServiceTest {
//
//    @Mock
//    private SlideRepository slideRepository;
//
//    @InjectMocks
//    private SlideService slideService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void getAllSlides_ReturnsAllSlides() {
//        List<Slide> expectedSlides = Arrays.asList(
//                Slide.builder().id(UUID.randomUUID()).title("Title 1").content("Content 1").orderIndex(1).build(),
//                Slide.builder().id(UUID.randomUUID()).title("Title 2").content("Content 2").orderIndex(2).build()
//        );
//        when(slideRepository.findAll()).thenReturn(expectedSlides);
//        List<Slide> actualSlides = slideService.getAllSlides();
//        assertEquals(expectedSlides.size(), actualSlides.size());
//        assertEquals(expectedSlides, actualSlides);
//    }
//
//    @Test
//    void getSlideById_WithValidId_ReturnsSlide() {
//        UUID id = UUID.randomUUID();
//        Slide expectedSlide = Slide.builder().id(id).title("Title").content("Content").orderIndex(1).build();
//        when(slideRepository.findById(id)).thenReturn(Optional.of(expectedSlide));
//        Optional<Slide> actualSlide = slideService.getSlideById(id);
//        assertTrue(actualSlide.isPresent());
//        assertEquals(expectedSlide, actualSlide.get());
//    }
//
//    @Test
//    void getSlideById_WithInvalidId_ReturnsEmpty() {
//        UUID id = UUID.randomUUID();
//        when(slideRepository.findById(id)).thenReturn(Optional.empty());
//        Optional<Slide> actualSlide = slideService.getSlideById(id);
//        assertFalse(actualSlide.isPresent());
//    }
//
//    @Test
//    void createSlide_ReturnsCreatedSlide() {
//        Slide slideToCreate = Slide.builder().title("New Slide").content("New Content").orderIndex(3).build();
//        Slide createdSlide = Slide.builder().id(UUID.randomUUID()).title("New Slide").content("New Content").orderIndex(3).build();
//        when(slideRepository.save(slideToCreate)).thenReturn(createdSlide);
//        Slide actualSlide = slideService.createSlide(slideToCreate);
//        assertEquals(createdSlide, actualSlide);
//    }
//
//    @Test
//    void updateSlide_WithValidId_ReturnsUpdatedSlide() {
//        UUID id = UUID.randomUUID();
//        Slide existingSlide = Slide.builder().id(id).title("Title").content("Content").orderIndex(1).build();
//        Slide updatedSlide = Slide.builder().id(id).title("Updated Title").content("Updated Content").orderIndex(2).build();
//        when(slideRepository.findById(id)).thenReturn(Optional.of(existingSlide));
//        when(slideRepository.save(existingSlide)).thenReturn(updatedSlide);
//        Slide actualSlide = slideService.updateSlide(id, updatedSlide);
//        assertEquals(updatedSlide, actualSlide);
//    }
//
//    @Test
//    void updateSlide_WithInvalidId_ReturnsNull() {
//        UUID id = UUID.randomUUID();
//        Slide updatedSlide = Slide.builder().id(id).title("Updated Title").content("Updated Content").orderIndex(2).build();
//        when(slideRepository.findById(id)).thenReturn(Optional.empty());
//        Slide actualSlide = slideService.updateSlide(id, updatedSlide);
//        assertNull(actualSlide);
//    }
//
//    @Test
//    void deleteSlide_WithValidId_DeletesSlide() {
//        UUID id = UUID.randomUUID();
//        slideService.deleteSlide(id);
//        verify(slideRepository, times(1)).deleteById(id);
//    }
//
//    @Test
//    void deleteSlide_WithInvalidId_DoesNothing() {
//        UUID id = UUID.randomUUID();
//        slideService.deleteSlide(id);
//        verify(slideRepository, times(1)).deleteById(id);
//    }
//
//}
