package ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.model;

import ahmed.test.monolithic.monolithic_mod.shared.domain.model.AggregateRoot;

public class Subject extends AggregateRoot<SubjectId> {

    private SubjectId subjectId;
    private String subjectName;
    private String language;

    private Subject(SubjectId subjectId, String subjectName, String language) {
        super(subjectId);
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.language = language;
    }

    public static Subject create(SubjectId subjectId, String subjectName, String language) {
        return new Subject( subjectId, subjectName, language);
    }


    public SubjectId getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getLanguage() {
        return language;
    }
}
