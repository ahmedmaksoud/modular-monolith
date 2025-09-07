package ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.gateways;

import ahmed.test.monolithic.monolithic_mod.subjects.shared.apis.ISubjectAPI;
import ahmed.test.monolithic.monolithic_mod.subjects.shared.dto.SubjectDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class SubjectGateway {
    private final ISubjectAPI  iSubjectAPI;
    public SubjectGateway(ISubjectAPI iSubjectAPI) {
        this.iSubjectAPI = iSubjectAPI;

    }
    public List<SubjectDTO> getByLanguageAndSubjectId(String language, Integer subjectId) {
        return iSubjectAPI.getByLanguageAndSubjectId(language, subjectId);
    }

    public Optional<SubjectDTO> getBySubjectId(Integer subjectId) {
        return iSubjectAPI.getBySubjectId(subjectId);
    }
}
