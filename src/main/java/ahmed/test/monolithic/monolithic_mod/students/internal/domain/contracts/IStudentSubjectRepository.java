package ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts;

import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentId;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentSubjects;
import ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.db.StudentSubjectsEntity;

import java.util.List;
import java.util.Optional;

public interface IStudentSubjectRepository {
    public List<StudentSubjects>  findByStudentId(StudentId studentId);

    public StudentSubjectsEntity save(StudentSubjects studentSubjects);
}
