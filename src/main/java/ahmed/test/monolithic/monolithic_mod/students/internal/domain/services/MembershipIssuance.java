package ahmed.test.monolithic.monolithic_mod.students.internal.domain.services;



import ahmed.test.monolithic.monolithic_mod.shared.exception.AlreadyIssuedMemberShip;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Membership;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class MembershipIssuance {
       public LocalDate issue(Student student, Clock clock, Period term){
        Optional<Membership> current = student.getMembership();
        current.ifPresent(val -> {
            throw new AlreadyIssuedMemberShip("MemberShip Already issued" + student.getStudentId().value());
        });
        LocalDate today = LocalDate.now(clock);
        Membership issued = new Membership(today, today.plus(term));
        return student.applyMembershipIssuance(issued);
    }

}
