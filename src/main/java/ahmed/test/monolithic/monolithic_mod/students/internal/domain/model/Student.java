package ahmed.test.monolithic.monolithic_mod.students.internal.domain.model;

import ahmed.test.monolithic.monolithic_mod.shared.domain.model.AggregateRoot;
import ahmed.test.monolithic.monolithic_mod.shared.exception.AlreadyEnrolledException;
import ahmed.test.monolithic.monolithic_mod.shared.exception.NotFoundException;
import ahmed.test.monolithic.monolithic_mod.students.shared.events.StudentRegistered;
import ahmed.test.monolithic.monolithic_mod.subjects.shared.dto.SubjectDTO;

import java.util.*;

public class Student extends AggregateRoot<StudentId> {
    private final StudentId studentId;           // or drop this and use getId()
    private final String firstName;
    private final String lastName;
    private final List<StudentSubjects> subjects;

    private Student(StudentId studentId, String firstName, String lastName, List<StudentSubjects> subjects) {
        super(studentId);
        this.studentId = studentId;
        this.firstName = Objects.requireNonNull(firstName, "firstName");
        this.lastName  = Objects.requireNonNull(lastName, "lastName");
        this.subjects  = (subjects != null) ? new ArrayList<>(subjects) : new ArrayList<>();
    }

    public static Student registerStudent(StudentProp p) {
        Student s = create(p);
        s.raiseEvent(StudentRegistered.from(s));
        return s;
    }

    public static Student create(StudentProp p) {
        return new Student(new StudentId(p.studentId()), p.firstName(), p.lastName(), p.studentSubjectsList());
    }

    public void enrollInSubject(Integer subjectId, SubjectDTO subjectDetails,
                                List<SubjectDTO> subjectsByLanguage, String userLanguage , List<StudentSubjects> repoSubjects) {

        validateSubjectEnrollment(subjectId, subjectDetails, subjectsByLanguage, userLanguage, repoSubjects);
        subjects.add(new StudentSubjects(null, subjectId, studentId));
    }

    private void validateSubjectEnrollment(Integer subjectId, SubjectDTO details,
                                           List<SubjectDTO> subjectsByLanguage, String userLanguage, List<StudentSubjects> repoSubjects) {
        if (details == null)
            throw new NotFoundException("No subject available based on subjectId " + subjectId);
        if (subjectsByLanguage == null || subjectsByLanguage.isEmpty())
            throw new NotFoundException("No subject available based on user language " + userLanguage);
        boolean exists = subjects.stream().anyMatch(e -> e.getSubjectId().equals(subjectId));
        if (exists) throw new AlreadyEnrolledException("user already enrolled");

        boolean existsInMemory = subjects != null
                && subjects.stream().anyMatch(e -> e.getSubjectId().equals(subjectId));

        boolean existsInRepo = repoSubjects.stream().anyMatch(e -> e.getSubjectId().equals(subjectId));

        if (existsInMemory || existsInRepo) throw new IllegalStateException("Already enrolled");

    }

    public List<StudentSubjects> getSubjects() {
        return Collections.unmodifiableList(subjects);
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}