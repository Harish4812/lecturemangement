BEGIN;

INSERT INTO users(id, name, email, phone_number)
VALUES
    (gen_random_uuid(), 'John Doe', 'john@example.com', '1234567890'),
    (gen_random_uuid(), 'Jane Smith', 'jane@example.com', '0987654321'),
    (gen_random_uuid(), 'Michael Johnson', 'michael@example.com', '9876543210'),
    (gen_random_uuid(), 'Emily Brown', 'emily@example.com', '0123456789'),
    (gen_random_uuid(), 'Chris Davis', 'chris@example.com', '6789012345');


INSERT INTO slide(id, title, content, order_index, created_at, updated_at, updated_by)
VALUES
    (gen_random_uuid(), 'Introduction', 'Welcome to the lecture', 1, NOW(), NOW(), (SELECT id FROM users LIMIT 1)),
    (gen_random_uuid(),  'Slide 2', 'Content for Slide 2', 2, NOW(), NOW(), (SELECT id FROM users LIMIT 1)),
    (gen_random_uuid(),  'Slide 3', 'Content for Slide 3', 3, NOW(), NOW(), (SELECT id FROM users LIMIT 1)),
    (gen_random_uuid(), 'Slide 4', 'Content for Slide 4', 4, NOW(), NOW(), (SELECT id FROM users LIMIT 1)),
    (gen_random_uuid(), 'Slide 5', 'Content for Slide 5', 5, NOW(), NOW(), (SELECT id FROM users LIMIT 1));



INSERT INTO teacher(id, name, email, phone_number, created_at, updated_at, updated_by, salary)
VALUES
    (gen_random_uuid(), 'Professor Smith', 'prof@example.com', '1234567890',  NOW(), NOW(), (SELECT id FROM users LIMIT 1), 100000),
    (gen_random_uuid(), 'Dr. Johnson', 'drjohnson@example.com', '0987654321',  NOW(), NOW(), (SELECT id FROM users LIMIT 1), 70000),
    (gen_random_uuid(), 'Sarah Miller', 'sarah@example.com', '6789012345',  NOW(), NOW(), (SELECT id FROM users LIMIT 1), 50000),
    (gen_random_uuid(), 'David Wilson', 'david@example.com', '5432109876',  NOW(), NOW(), (SELECT id FROM users LIMIT 1), 80000),
    (gen_random_uuid(), 'Jennifer Lee', 'jennifer@example.com', '3456789012',  NOW(), NOW(), (SELECT id FROM users LIMIT 1), 67000);


INSERT INTO lecture(id, title, description, date_of_lecture, location, duration_minutes, created_at, updated_at, updated_by)
VALUES
    (gen_random_uuid(),  'Introduction to Spring Boot', 'This lecture covers the basics of Spring Boot framework', '2024-02-18 10:00:00', 'Online', 60, NOW(), NOW()),
    (gen_random_uuid(),  'Database Design', 'Introduction to database design principles', '2024-02-20 14:00:00', 'Room 101', 90, NOW(), NOW()),
    (gen_random_uuid(),  'Java Programming', 'Basics of Java programming language', '2024-02-22 09:00:00', 'Lab 202', 120, NOW(), NOW()),
    (gen_random_uuid(),  'Web Development', 'Introduction to web development technologies', '2024-02-24 13:00:00', 'Auditorium A', 90, NOW(), NOW()),
    (gen_random_uuid(),  'Machine Learning', 'Fundamentals of machine learning algorithms', '2024-02-26 15:00:00', 'Conference Room B', 120, NOW(), NOW());


--many to many mapping
INSERT INTO lecture_teachers(lecture_id, teachers_id)
VALUES
    (SELECT id FROM lecture LIMIT 1, SELECT id FROM teacher LIMIT 1);


--one to many mapping
INSERT INTO lecture_slides(lecture_id, slides_id)
VALUES
   (SELECT id FROM lecture LIMIT 1, SELECT id FROM slide LIMIT 1);

--many to many mapping

INSERT INTO teacher_lectures(teacher_id, lectures_id)
VALUES
   (SELECT id FROM teacher LIMIT 1, SELECT id FROM lecture LIMIT 1);


INSERT INTO migrations(id, file_name, created_at) VALUES (gen_random_uuid(), 'lecture_slide_teacher_insertions.sql', NOW());

COMMIT;
