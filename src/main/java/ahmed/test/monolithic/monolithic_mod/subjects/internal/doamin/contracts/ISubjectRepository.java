package ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.contracts;

import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.model.Subject;
import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.model.SubjectId;
import org.springframework.lang.Contract;

import java.util.List;
import java.util.Optional;

public interface ISubjectRepository {

    public List<Subject> findByLanguageAndSubjectId(String language, Integer subjectId);

    public Optional<Subject> findBySubjectId(SubjectId subjectId);

}
