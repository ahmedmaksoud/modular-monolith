CREATE TABLE student_subjects
(
    student_subject_id INTEGER NOT NULL,
    subject_id          INTEGER,
    student_id          INTEGER,
    CONSTRAINT pk_student_subjects PRIMARY KEY (student_subject_id)
);