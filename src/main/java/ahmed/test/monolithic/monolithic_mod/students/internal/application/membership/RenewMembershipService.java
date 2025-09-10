package ahmed.test.monolithic.monolithic_mod.students.internal.application.membership;

import ahmed.test.monolithic.monolithic_mod.shared.exception.NotFoundException;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts.IStudentRepository;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Membership;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentId;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.services.ExtendFromMaxPolicy;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.services.MembershipRenewal;
import ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;

@Service
public class RenewMembershipService {
    private final IStudentRepository repo;

    public RenewMembershipService(IStudentRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void renewMembership(Integer rawStudentId) {

        Clock clock = Clock.systemDefaultZone();
        LocalDate today = LocalDate.now(clock);
        Period defaultTerm = Period.ofMonths(12);


        Student student = repo.findByStudentId (new StudentId(rawStudentId))
                .orElseThrow(() -> new NotFoundException("Student not found: " + rawStudentId));
        ExtendFromMaxPolicy renewalPolicy = new ExtendFromMaxPolicy();

        MembershipRenewal membershipRenewal =  new MembershipRenewal(renewalPolicy);

        membershipRenewal.renew(student, clock, defaultTerm);
        repo.saveStudent(student);

    }
}