package ahmed.test.monolithic.monolithic_mod.subjects.shared.apis;

import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.model.Subject;
import ahmed.test.monolithic.monolithic_mod.subjects.shared.dto.SubjectDTO;

import java.util.List;
import java.util.Optional;

public interface ISubjectAPI {

    List<SubjectDTO> getByLanguageAndSubjectId(String language,  Integer subjectId);

    SubjectDTO getBySubjectId(Integer subjectId);
}
