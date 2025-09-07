package ahmed.test.monolithic.monolithic_mod.students.internal.domain.model;

import java.util.List;

public record StudentProp(Integer studentId,
                          String firstName,
                          String lastName
                          ,List<StudentSubjects> studentSubjectsList
    ) {

    public StudentProp {

    }
}
