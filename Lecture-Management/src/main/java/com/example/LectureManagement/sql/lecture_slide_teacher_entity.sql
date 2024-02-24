BEGIN;

CREATE TABLE users(
   id UUID PRIMARY KEY,
   name TEXT NOT NULL,
   email TEXT,
   phone_number TEXT
);

CREATE TABLE slide(
   id UUID PRIMARY KEY,
   title TEXT NOT NULL,
   content TEXT,
   order_index INTEGER,
   created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
   updated_at timestamptz DEFAULT CURRENT_TIMESTAMP,
   updated_by UUID REFERENCES users(id)
);

CREATE TABLE teacher(
   id UUID PRIMARY KEY,
   name TEXT NOT NULL,
   email TEXT,
   phone_number TEXT,
   salary INTEGER CHECK (salary > 0),
   created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
   updated_at timestamptz DEFAULT CURRENT_TIMESTAMP,
   updated_by UUID REFERENCES users(id)
);

CREATE TABLE lecture(
    id UUID PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT,
    date_of_lecture timestamptz,
    location TEXT,
    duration_minutes INTEGER CHECK (duration_minutes > 0),
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID REFERENCES users(id)
);

CREATE TABLE migrations(
   id UUID PRIMARY KEY,
   file_name TEXT,
   created_at timestamptz DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE lecture_slides(
   lecture_id UUID,
   slides_id UUID
)

CREATE TABLE lecture_teachers(
   lecture_id UUID,
   teachers_id UUID
)

CREATE TABLE teacher_lectures(
   teacher_id UUID,
   lectures_id UUID
)


-- Mapping tables
--lecture_teachers - lecture_id, teachers_id
--lecture_slides - lecture_id, slides_id
--teacher_lectures - teacher_id, lectures_id


INSERT INTO migrations(id, file_name, created_at) VALUES (gen_random_uuid(), 'lecture_slide_teacher_entity.sql', NOW());

COMMIT;
