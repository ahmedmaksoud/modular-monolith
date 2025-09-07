package ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.db;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentIdSeq")
    @SequenceGenerator(name = "studentIdSeq", sequenceName = "studentIdSeq", allocationSize = 1)
    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "first_name", length = Integer.MAX_VALUE)
    private String firstName;

    @Column(name = "last_name", length = Integer.MAX_VALUE)
    private String lastName;

    public List<StudentSubjectsEntity> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(List<StudentSubjectsEntity> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentSubjectsEntity> studentSubjects;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



}