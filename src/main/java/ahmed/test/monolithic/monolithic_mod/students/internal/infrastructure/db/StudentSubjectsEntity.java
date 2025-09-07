package ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.db;

import jakarta.persistence.*;

@Entity
@Table(name = "student_subjects")

public class StudentSubjectsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSubjectIdSeq")
    @SequenceGenerator(name = "studentSubjectIdSeq", sequenceName = "studentSubjectIdSeq", allocationSize = 1)
    private Integer studentSubjectId;


    private Integer subjectId;

    @Column(name = "student_id")
    private Integer studentId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity student;

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public Integer getStudentSubjectId() {
        return studentSubjectId;
    }

    public void setStudentSubjectId(Integer studentdSubjectId) {
        this.studentSubjectId = studentdSubjectId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}