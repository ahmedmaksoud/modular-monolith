package ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.mappers;

import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentSubjectsProp;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentSubjects;
import ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.db.StudentSubjectsEntity;
import org.springframework.stereotype.Component;

@Component
public final class StudentSubjectsMapper {


    // Entity -> Domain (rehydration)
    public StudentSubjects toDomain(StudentSubjectsEntity e) {
        if (e == null) return null;
        // uses the domain factory (private ctor is respected)
        return StudentSubjects.create(new StudentSubjectsProp(
                e.getStudentSubjectId(),
                e.getSubjectId(),
                e.getStudentId()
        ));
    }
    // Domain -> Entity (persistence)
    public  StudentSubjectsEntity toEntity(StudentSubjects d) {
        if (d == null) return null;
        var e = new StudentSubjectsEntity();
        if (d.getStudentSubjectsId() != null) e.setStudentSubjectId(d.getStudentSubjectsId().value());
        e.setStudentId(d.getStudentId().value());

        e.setSubjectId(d.getSubjectId()); // or d.getFirstName() if you have getters

        return e;
    }
}
