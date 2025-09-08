package ahmed.test.monolithic.monolithic_mod.students.internal.application.registration;

import ahmed.test.monolithic.monolithic_mod.shared.IApplogger;
import ahmed.test.monolithic.monolithic_mod.shared.domain.ApplicationService;
import ahmed.test.monolithic.monolithic_mod.shared.exception.NotFoundException;
import ahmed.test.monolithic.monolithic_mod.shared.infrastructure.AppLoggers;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts.IStudentRepository;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts.IStudentSubjectRepository;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentId;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentProp;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentSubjects;
import ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.gateways.SubjectGateway;
import ahmed.test.monolithic.monolithic_mod.subjects.shared.dto.SubjectDTO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrarStudentService extends ApplicationService {

    private final IApplogger log = AppLoggers.get(this.getClass());
    private final IStudentRepository studentRepository;
    private final ApplicationEventPublisher events;
    private final SubjectGateway subjectGateway;
    private final IStudentSubjectRepository studentSubjectRepository;

    public RegistrarStudentService(IStudentRepository iStudentRepository, ApplicationEventPublisher events,
                                   SubjectGateway subjectGateway, IStudentSubjectRepository studentSubjectRepository) {
        this.studentRepository = iStudentRepository;
        this.events = events;
        this.subjectGateway = subjectGateway;
        this.studentSubjectRepository = studentSubjectRepository;
    }

    /**
     *
     * @param registerStudent
     * @return
     */
    @Transactional
    public StudentResponse registerStudent(RegisterStudent registerStudent) {
        Integer studNextVal = studentRepository.getNextStudentId();
        var student = Student.registerStudent(new StudentProp(
                studNextVal,
                registerStudent.firstName(), registerStudent.lastName()
                , registerStudent.registerSubject() != null  ? asList(registerStudent.registerSubject().toDomain(studNextVal)) : null
        ));
        Student saveStudent = studentRepository.saveStudent(student);

        student.occurredEvents().forEach(e -> {
            events.publishEvent(e);
        });

        return new StudentResponse(saveStudent.getStudentId().value());
    }

    private List<StudentSubjects> asList(StudentSubjects domain) {
        ArrayList<StudentSubjects> arr = new ArrayList<>();
               arr.add(domain);
        return arr;
    }

    /**
     *
     * @param registerSubject
     * @return
     */
    @Transactional
    public RegisterSubjectResponse registerStudentSubject(RegisterSubject registerSubject) {
        String userLang = "ar";
        Optional<Student> student = studentRepository.findByStudentId(new StudentId(registerSubject.studentId()));

        if (!student.isPresent()) {
            throw new NotFoundException("This student is not found");
        }


        List<SubjectDTO> subjectsByLanguage =  subjectGateway.getByLanguageAndSubjectId(userLang, registerSubject.subjectId());

        SubjectDTO addedSubjectDetails = subjectGateway.getBySubjectId(registerSubject.subjectId());
        List<StudentSubjects>  studentSubjects = studentSubjectRepository.findByStudentId(student.get().getStudentId());
        student.get().enrollInSubject(registerSubject.subjectId(), addedSubjectDetails, subjectsByLanguage,  userLang, studentSubjects); ;
        //.get().getStudendSubjectsList()

        student.get().getSubjects().stream().forEach(s -> {
            studentSubjectRepository.save(s);
        });

        return new RegisterSubjectResponse(registerSubject.subjectId());
    }
}
