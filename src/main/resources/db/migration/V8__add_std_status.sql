


ALTER TABLE student
    ADD student_status INTEGER;

update student set student_status = 1;

ALTER TABLE student
    ALTER COLUMN student_status SET NOT NULL;