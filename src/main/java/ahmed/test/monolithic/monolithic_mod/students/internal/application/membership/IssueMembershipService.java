package ahmed.test.monolithic.monolithic_mod.students.internal.application.membership;

import ahmed.test.monolithic.monolithic_mod.shared.application.BusinessRuleEngineService;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.User;
import ahmed.test.monolithic.monolithic_mod.shared.exception.NotFoundException;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts.IStudentRepository;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentId;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.services.MembershipIssuance;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;

@Service
public class IssueMembershipService {
    private final IStudentRepository repo;
    private final ApplicationEventPublisher events;
    private final BusinessRuleEngineService businessRuleEngineService;


    public IssueMembershipService(IStudentRepository repo, ApplicationEventPublisher events,
                                  BusinessRuleEngineService businessRuleEngineService) {
        this.repo = repo;
        this.events = events;
        this.businessRuleEngineService = businessRuleEngineService;
    }

    @Transactional(rollbackFor = Exception.class)
    public IssueMembershipResponse issueMemberShip(IssueMembershipRequest issueMembershipRequest) {
        Student student = repo.findByStudentId (new StudentId(issueMembershipRequest.studentId()))
                .orElseThrow(() -> new NotFoundException("Student not found: " + issueMembershipRequest.studentId()));

        Clock clock = Clock.systemDefaultZone();
        Period defaultTerm = Period.ofYears(1);
        User user = new User();
        MembershipIssuance membershipIssuance =  new MembershipIssuance();
        LocalDate expDate =  membershipIssuance.issue(student, clock, defaultTerm, user, businessRuleEngineService.getBusinessRules());
        repo.saveStudent(student);
        student.occurredEvents().forEach(e -> {
            events.publishEvent(e);
        });

        return new IssueMembershipResponse(expDate);
    }
}