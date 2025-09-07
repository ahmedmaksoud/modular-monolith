package ahmed.test.monolithic.monolithic_mod.subjects.internal.infrastructure.db;

import jakarta.persistence.*;

@Entity
@Table(name = "subject")
public class SubjectEntity {

    @Id
    private Integer subjectId;
    private String subjectName;
    private String language;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}