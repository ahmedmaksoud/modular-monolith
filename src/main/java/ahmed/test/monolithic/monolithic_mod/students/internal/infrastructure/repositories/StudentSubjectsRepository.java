package ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.repositories;

import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentId;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentSubjects;
import ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.db.StudentSubjectsEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface StudentSubjectsRepository extends ListCrudRepository<StudentSubjectsEntity, Integer> {

    List<StudentSubjectsEntity> findByStudentId(Integer studentId);
}