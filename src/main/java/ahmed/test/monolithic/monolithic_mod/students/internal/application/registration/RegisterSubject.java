package ahmed.test.monolithic.monolithic_mod.students.internal.application.registration;

import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentId;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentSubjects;

import java.io.Serializable;

public record RegisterSubject(Integer subjectId, Integer studentId) implements Serializable {

    public StudentSubjects toDomain(Integer studentId) {
        return new StudentSubjects(null,subjectId, new StudentId(studentId));
    }
}
