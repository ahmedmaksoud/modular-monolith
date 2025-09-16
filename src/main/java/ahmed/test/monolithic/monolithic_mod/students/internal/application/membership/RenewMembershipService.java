package ahmed.test.monolithic.monolithic_mod.students.internal.application.membership;

import ahmed.test.monolithic.monolithic_mod.shared.exception.NotFoundException;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts.IStudentRepository;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentId;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.services.ExtendFromMaxPolicy;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.services.MembershipRenewal;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;

@Service
public class RenewMembershipService {
    private final IStudentRepository repo;
    private final ApplicationEventPublisher events;


    public RenewMembershipService(IStudentRepository repo, ApplicationEventPublisher events) {
        this.repo = repo;
        this.events = events;
    }

    @Transactional(rollbackFor = Exception.class)
    public RenewMembershipResponse renewMembership(RenewMembershipRequest renewMembershipRequest) {
        Student student = repo.findByStudentId (new StudentId(renewMembershipRequest.studentId()))
                .orElseThrow(() -> new NotFoundException("Student not found: " + renewMembershipRequest.studentId()));
        Clock clock = Clock.systemDefaultZone();
        LocalDate today = LocalDate.now(clock);
        Period defaultTerm = Period.ofYears(1);

        ExtendFromMaxPolicy renewalPolicy = new ExtendFromMaxPolicy();
        MembershipRenewal membershipRenewal =  new MembershipRenewal(renewalPolicy);

        LocalDate expDate =  membershipRenewal.renew(student, clock, defaultTerm);
        repo.saveStudent(student);
        student.occurredEvents().forEach(e -> {
            events.publishEvent(e);
        });

        return new RenewMembershipResponse(expDate);
    }
}