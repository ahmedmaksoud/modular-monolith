package ahmed.test.monolithic.monolithic_mod.students.internal.controllers;


import ahmed.test.monolithic.monolithic_mod.shared.IApplogger;
import ahmed.test.monolithic.monolithic_mod.shared.infrastructure.AppLoggers;
import ahmed.test.monolithic.monolithic_mod.students.internal.application.registration.RegisterSubject;
import ahmed.test.monolithic.monolithic_mod.students.internal.application.registration.RegisterStudent;
import ahmed.test.monolithic.monolithic_mod.students.internal.application.registration.RegisterSubjectResponse;
import ahmed.test.monolithic.monolithic_mod.students.internal.application.registration.RegistrarStudentService;
import ahmed.test.monolithic.monolithic_mod.students.internal.application.registration.StudentResponse;
import ahmed.test.monolithic.monolithic_mod.students.internal.services.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
 class StudentController {

    private StudentService studentService;
    private RegistrarStudentService registrarStudentService;

    private IApplogger logger = AppLoggers.get(StudentController.class);


    public StudentController(StudentService studentService, RegistrarStudentService registrarStudentService) {
        this.studentService = studentService;
        this.registrarStudentService = registrarStudentService;
    }

    @PostMapping("updateStudent/{studentId}")
    void updateStudent(@PathVariable int studentId, @RequestParam String firstName, @RequestParam String lastName) {
        studentService.updateStudent(studentId, firstName, lastName);
    }



    @PostMapping("/v1/registerStudent")
    StudentResponse updateStudent( @RequestBody RegisterStudent registerStudent) {
        logger.info(">>>>>>>>>>>>>>>>>  updateStudent started");
        return  registrarStudentService.registerStudent(registerStudent);
    }



    @PostMapping("/v1/addSubject")
    RegisterSubjectResponse registerStudentSubject(@RequestBody RegisterSubject registerSubject) {
        logger.info(">>>>>>>>>>>>>>>>>  updateStudent started");
        return  registrarStudentService.registerStudentSubject(registerSubject);
    }


}
