package ahmed.test.monolithic.monolithic_mod.subjects.shared.apis;

import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.contracts.ISubjectRepository;
import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.model.Subject;
import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.model.SubjectId;
import ahmed.test.monolithic.monolithic_mod.subjects.internal.infrastructure.mappers.SubjectMapper;
import ahmed.test.monolithic.monolithic_mod.subjects.shared.dto.SubjectDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SubjectAPI implements ISubjectAPI{


    private final ISubjectRepository  subjectRepository;
    private final SubjectMapper subjectMapper;
    public SubjectAPI(ISubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    public List<SubjectDTO> getByLanguageAndSubjectId(String language, Integer subjectId){
        return subjectRepository.findByLanguageAndSubjectId(language, subjectId).stream().map(subjectMapper::toDTO).toList();
    }

    @Override
    public SubjectDTO getBySubjectId(Integer subjectId) {
        return subjectMapper.toDTO(subjectRepository.findBySubjectId(new SubjectId( subjectId)));
    }
}
