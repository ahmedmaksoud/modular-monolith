package ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.repositories;


import ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts.IStudentRepository;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;

import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentId;
import ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.db.StudentEntity;
import ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.mappers.StudentMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class StudentJpaRepository implements IStudentRepository {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper; // or remove and use StudentAssembler

    public StudentJpaRepository(StudentRepository studentRepository, StudentMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Student> findByStudentId(StudentId studentId) {
        Optional<StudentEntity> e = studentRepository.findByStudentId(studentId.value());
        return e.map(mapper::toDomain);// or StudentAssembler.toDomain(e)
    }

    @Override
    public List<Student> findByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName)
                .stream()
                .map(mapper::toDomain) // or StudentAssembler::toDomain
                .toList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        return studentRepository.findByLastName(lastName)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Integer getNextStudentId() {
        return studentRepository.getNextStudentId();
    }

    @Override
    public Student saveStudent(Student student) {
      StudentEntity studentEntity =   studentRepository.save(mapper.toEntity(student));
      return mapper.toDomain(studentEntity);
    }


    public static void main(String[] args) {
        Optional<StudentEntity> e = Optional.ofNullable(null);
        System.out.println(e.map(StudentEntity::getFirstName).orElse(""));
    }
}
