


CREATE TABLE subject
(
    subject_id   INTEGER NOT NULL,
    subject_name VARCHAR(255),
    CONSTRAINT pk_subject PRIMARY KEY (subject_id)
);

ALTER TABLE student
    ADD CONSTRAINT pk_student PRIMARY KEY (student_id);

ALTER TABLE student
ALTER
COLUMN first_name TYPE TEXT USING (first_name::TEXT);

ALTER TABLE student
ALTER
COLUMN last_name TYPE TEXT USING (last_name::TEXT);