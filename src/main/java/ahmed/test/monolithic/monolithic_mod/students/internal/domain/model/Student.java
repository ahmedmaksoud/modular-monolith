package ahmed.test.monolithic.monolithic_mod.students.internal.domain.model;

import ahmed.test.monolithic.monolithic_mod.shared.domain.model.AggregateRoot;
import ahmed.test.monolithic.monolithic_mod.shared.exception.NotFoundException;
import ahmed.test.monolithic.monolithic_mod.students.shared.events.StudentRegistered;
import ahmed.test.monolithic.monolithic_mod.subjects.shared.dto.SubjectDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Student extends AggregateRoot<StudentId> {

    private StudentId studentId;
    private String firstName;
    private String lastName;
    private List<StudentSubjects> studendSubjectsList;


    private Student(StudentId studentId, String firstName, String lastName, List<StudentSubjects> studendSubjectsList) {
        super(studentId);
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studendSubjectsList = studendSubjectsList;
        if (studendSubjectsList == null) {studendSubjectsList = new ArrayList<>();}
      //  if (!studendSubjectsList.isEmpty()) validateSubjects(studendSubjectsList);
    }



    public static Student registerStudent(StudentProp studentProp) {

        Student student = create(studentProp);
        student.raiseEvent(StudentRegistered.from(student));
        return student;
    }

    public static Student create(StudentProp studentProp) {


        return new Student(
                new StudentId(studentProp.studentId()),
                studentProp.firstName(),
                studentProp.lastName(),
                studentProp.studentSubjectsList()

        );
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

    public List<StudentSubjects> getStudendSubjectsList() {
        return studendSubjectsList;
    }

    public void registerStudentSubjects(Optional<List<StudentSubjects>> studentSubjects, Optional<SubjectDTO> addedSubjectDetails,
                                        Integer  subjectId, List<SubjectDTO> subjectsByLanguage, String userLanguage) {

        validateSubjects(studentSubjects, addedSubjectDetails, subjectId, subjectsByLanguage, userLanguage);


        StudentSubjects studentSubjectsAdded = new StudentSubjects(null, subjectId, studentId);
        //studentSubjects.get().add(studentSubjectsAdded);
        studendSubjectsList.add(studentSubjectsAdded);

    }

    private void validateSubjects(Optional<List<StudentSubjects>> studentSubjects, Optional<SubjectDTO> addedSubjectDetails, Integer subjectId, List<SubjectDTO> subjectsByLanguage, String userLanguage) {
        if (studendSubjectsList == null) studendSubjectsList = new ArrayList<>();
        if (!studentSubjects.isPresent()) studentSubjects = Optional.of(new ArrayList<>());

        if (!addedSubjectDetails.isPresent())
            throw new NotFoundException("No subject available based on subjectId " + subjectId);

        if (subjectsByLanguage == null || subjectsByLanguage.isEmpty())
            throw new NotFoundException("No subject available based on user language " + userLanguage);


        boolean exists = studentSubjects.get().stream().anyMatch(e -> e. getSubjectId().equals(subjectId));
        if (exists) throw new IllegalStateException("Already enrolled");
    }
}
