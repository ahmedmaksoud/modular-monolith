package ahmed.test.monolithic.monolithic_mod.subjects.internal.infrastructure.repositories;

import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.contracts.ISubjectRepository;
import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.model.Subject;
import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.model.SubjectId;
import ahmed.test.monolithic.monolithic_mod.subjects.internal.infrastructure.mappers.SubjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubjectJpaRepository implements ISubjectRepository {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    public SubjectJpaRepository(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public List<Subject> findByLanguageAndSubjectId(String language, Integer subjectId) {
        return subjectRepository.findByLanguageAndSubjectId(language,subjectId).stream()
                .map( subjectMapper::toDomain).toList();
    }

    @Override
    public Optional<Subject> findBySubjectId(SubjectId subjectId) {
        return subjectRepository.findBySubjectId(subjectId.value())
                .map( subjectMapper::toDomain);
    }
}
