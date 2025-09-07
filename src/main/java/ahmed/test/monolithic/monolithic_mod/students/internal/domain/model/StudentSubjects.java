package ahmed.test.monolithic.monolithic_mod.students.internal.domain.model;

import ahmed.test.monolithic.monolithic_mod.shared.domain.model.Entity;
import ahmed.test.monolithic.monolithic_mod.subjects.internal.doamin.model.SubjectId;


public class StudentSubjects extends Entity<StudentSubjectsId> {

    private StudentSubjectsId studentSubjectsId;
    private Integer subjectId;
    private StudentId studentId;

    public StudentSubjects(StudentSubjectsId id, Integer subjectId, StudentId studentId) {

        super(id);
        this.studentSubjectsId = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
    }



    public static StudentSubjects create(StudentSubjectsProp studentSubjectsProp) {
        return new StudentSubjects(new StudentSubjectsId( studentSubjectsProp.studentSubjectId()),
                studentSubjectsProp.subjectId(), new StudentId(studentSubjectsProp.studentId()));
    }

    public StudentSubjectsId getStudentSubjectsId() {
        return studentSubjectsId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public StudentId getStudentId() {
        return studentId;
    }
}
