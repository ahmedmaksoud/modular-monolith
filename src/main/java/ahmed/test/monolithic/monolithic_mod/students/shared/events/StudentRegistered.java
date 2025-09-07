package ahmed.test.monolithic.monolithic_mod.students.shared.events;

import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentId;

import java.util.Date;

public record StudentRegistered (Integer studentId, String studentFirstName, String studentLastName, Date created) implements StudentEvent {

    public static StudentRegistered from(Student student) {
        return new StudentRegistered(student.getId().value(),
                student.getFirstName(),
                student.getLastName(),
                new Date());
    }
}
