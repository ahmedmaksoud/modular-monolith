package ahmed.test.monolithic.monolithic_mod.students.internal.domain.services;

import ahmed.test.monolithic.monolithic_mod.shared.exception.AlreadyEnrolledException;
import ahmed.test.monolithic.monolithic_mod.shared.exception.AlreadIssuedMemberShip;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Membership;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

public class MembershipIsssuance {
       public LocalDate issue(Student student, Clock clock, Period term) throws AlreadyEnrolledException{
        Optional<Membership> current = student.getMembership();
        current.ifPresent(val -> {
            throw new AlreadyEnrolledException("MemberShip Already issued" + student.getStudentId().value());
        });
        LocalDate today = LocalDate.now(clock);
        Membership issued = new Membership(today, today.plus(term));
        return student.applyMembershipIssuance(issued);
    }

}
