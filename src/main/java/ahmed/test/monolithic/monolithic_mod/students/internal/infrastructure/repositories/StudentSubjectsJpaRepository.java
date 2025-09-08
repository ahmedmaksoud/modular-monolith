package ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.repositories;

import ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts.IStudentRepository;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts.IStudentSubjectRepository;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentId;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentSubjects;
import ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.db.StudentSubjectsEntity;
import ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.mappers.StudentSubjectsMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentSubjectsJpaRepository implements IStudentSubjectRepository {
    private final StudentSubjectsRepository  studentSubjectsRepository;
    private final StudentSubjectsMapper studentSubjectsMapper;


    public StudentSubjectsJpaRepository(StudentSubjectsRepository studentSubjectsRepository, StudentSubjectsMapper studentSubjectsMapper) {
        this.studentSubjectsRepository = studentSubjectsRepository;
        this.studentSubjectsMapper = studentSubjectsMapper;
    }

    @Override
    public List<StudentSubjects> findByStudentId(StudentId studentId) {
        return studentSubjectsRepository.findByStudentId(studentId.value()).stream()
                .map(studentSubjectsMapper::toDomain) // or StudentAssembler::toDomain
                .toList();

    }


    public StudentSubjectsEntity save(StudentSubjects studentSubjects) {
        StudentSubjectsEntity studentSubjectsEntity = studentSubjectsRepository.
                save(studentSubjectsMapper.toEntity(studentSubjects));
        studentSubjectsMapper.toDomain(studentSubjectsEntity);
        return studentSubjectsEntity;
    }

}
