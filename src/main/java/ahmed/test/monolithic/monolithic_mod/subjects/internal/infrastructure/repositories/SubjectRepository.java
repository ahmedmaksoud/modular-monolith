package ahmed.test.monolithic.monolithic_mod.subjects.internal.infrastructure.repositories;

import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.model.Subject;
import ahmed.test.monolithic.monolithic_mod.subjects.internal.infrastructure.db.SubjectEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends ListCrudRepository<SubjectEntity, Integer> {

    List<SubjectEntity> findByLanguageAndSubjectId(String language, Integer subjectId);


    Optional<SubjectEntity> findBySubjectId(Integer integer);

}