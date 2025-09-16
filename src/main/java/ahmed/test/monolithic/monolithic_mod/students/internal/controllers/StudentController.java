package ahmed.test.monolithic.monolithic_mod.students.internal.controllers;


import ahmed.test.monolithic.monolithic_mod.shared.IApplogger;
import ahmed.test.monolithic.monolithic_mod.shared.infrastructure.AppLoggers;
import ahmed.test.monolithic.monolithic_mod.students.internal.application.membership.*;
import ahmed.test.monolithic.monolithic_mod.students.internal.application.registration.RegisterSubject;
import ahmed.test.monolithic.monolithic_mod.students.internal.application.registration.RegisterStudent;
import ahmed.test.monolithic.monolithic_mod.students.internal.application.registration.RegisterSubjectResponse;
import ahmed.test.monolithic.monolithic_mod.students.internal.application.registration.RegistrarStudentService;
import ahmed.test.monolithic.monolithic_mod.students.internal.application.registration.StudentResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
 class StudentController {

    private RegistrarStudentService registrarStudentService;
    private RenewMembershipService renewMembershipService;
    private IssueMembershipService issueMembershipService;

    private IApplogger logger = AppLoggers.get(StudentController.class);


    public StudentController( RegistrarStudentService registrarStudentService, RenewMembershipService renewMembershipService, IssueMembershipService issueMembershipService) {
        this.registrarStudentService = registrarStudentService;
        this.renewMembershipService = renewMembershipService;
        this.issueMembershipService = issueMembershipService;
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


    @PostMapping("/v1/renewMemberShip")
    RenewMembershipResponse renewMemberShip(@RequestBody RenewMembershipRequest renewMembershipRequest) {
        logger.info(">>>>>>>>>>>>>>>>>  renewMemberShip started");
        return  renewMembershipService.renewMembership(renewMembershipRequest);
    }

    @PostMapping("/v1/issueMemberShip")
    IssueMembershipResponse issueMemberShip(@RequestBody IssueMembershipRequest issueMembershipRequest) {
        logger.info(">>>>>>>>>>>>>>>>>  issueMemberShip started");
        return  issueMembershipService.issueMemberShip(issueMembershipRequest);
    }

}
