package ahmed.test.monolithic.monolithic_mod.subjects.internal.infrastructure.mappers;

import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.model.Subject;
import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.model.SubjectId;
import ahmed.test.monolithic.monolithic_mod.subjects.internal.infrastructure.db.SubjectEntity;
import ahmed.test.monolithic.monolithic_mod.subjects.shared.dto.SubjectDTO;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    // --- JPA -> Domain
    public Subject toDomain(SubjectEntity e) {
        return Subject.create(
            //new SubjectId(e.getId()),
            new SubjectId(e.getSubjectId()),
            e.getSubjectName(),
            e.getLanguage()
        );
    }

    // --- Domain -> JPA
    public SubjectEntity toEntity(Subject d) {
        var e = new SubjectEntity();

        // If AggregateRoot exposes id() use that; otherwise switch to d.getId()
        e.setSubjectId(d.getSubjectId().value());                // or d.getId().value()
        e.setSubjectId(d.getSubjectId().value());
        e.setSubjectName(d.getSubjectName());
        e.setLanguage(d.getLanguage());
        return e;
    }

    public SubjectDTO toDTO(SubjectEntity e) {
        return new SubjectDTO(
                //new SubjectId(e.getId()),
               e.getSubjectId(),
                e.getSubjectName(),
                e.getLanguage()
        );
    }

    public SubjectDTO toDTO(Subject  e) {
        return new SubjectDTO(
                //new SubjectId(e.getId()),
                e.getSubjectId().value(),
                e.getSubjectName(),
                e.getLanguage()
        );
    }
}
