My project involves managing lectures, slides, and teachers. Here's a breakdown of how these components are related and their roles within the project:

LectureController: This controller manages endpoints related to lectures. It handles operations such as retrieving all lectures, getting a specific lecture by its ID, creating new lectures, updating existing lectures, and deleting lectures. Each lecture likely contains information such as its ID, title, content, associated slides, and related teachers.

SlideController: This controller is responsible for managing slides. It handles operations such as retrieving all slides, getting a specific slide by its ID, creating new slides, updating existing slides, and deleting slides. Slides are typically associated with lectures, meaning each lecture may contain multiple slides that are presented during the lecture session.

TeacherController: This controller would handle operations related to teachers, such as retrieving all teachers, getting a specific teacher by their ID, creating new teachers, updating existing teacher profiles, and deleting teachers.

SlideService: This service likely encapsulates the business logic related to slides. It may handle operations such as retrieving slides from a repository, creating new slides, updating slide information, and deleting slides. Additionally, it include methods to associate slides with lectures, retrieve slides belonging to a specific lecture, etc.

LectureService: Similar to the SlideService, the LectureService is responsible for handling the business logic related to lectures. It involve operations such as retrieving lectures from a repository, creating new lectures, updating lecture information, deleting lectures, associating slides with lectures, retrieving all slides for a particular lecture, etc.

TeacherService: This service layer would handle business logic related to teachers. It could include operations such as retrieving teacher information from a repository, creating new teacher profiles, updating teacher details, deleting teachers, and possibly associating teachers with lectures.

Exception Handling: The provided ExceptionHandle class manages exception handling within the project. It catches specific exceptions, such as ResourceNotFoundException, and returns appropriate error responses to clients. This helps in providing meaningful error messages and maintaining a robust and reliable API.

Overall, in my project, lectures serve as the main entities that contain educational content, slides complement lectures by providing visual aids and content structure, and teachers are responsible for delivering lectures and guiding students through the learning process. Each component plays a crucial role in facilitating effective teaching and learning experiences within the system.


Tech Stacks
Programming Language - Java
Framework - Spring Boot
Database - PostgreSQL
Driver - JDBI
File Architecture - Hexagonal
Flow - Controller -> Service -> DAL
DTO - Request And Response
