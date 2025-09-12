package ahmed.test.monolithic.monolithic_mod.students.internal.domain.services;

import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Membership;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class MembershipRenewal {
    private final ExtendFromMaxPolicy renewalPolicy;

    public MembershipRenewal(ExtendFromMaxPolicy renewalPolicy) {
        this.renewalPolicy = Objects.requireNonNull(renewalPolicy);
    }


    public LocalDate renew(Student student, Clock clock, Period term) {
        Membership current = student.getMembership()
                .orElseThrow(() -> new IllegalStateException("No membership to renew—issue first."));

        LocalDate today = LocalDate.now(clock);
        LocalDate base = renewalPolicy.baseDateForRenewal(today, current.expiryDate());

        // issueDate becomes the action date (today), expiry extends from base
        Membership renewed = new Membership(today, base.plus(term));


        return student.applyMembershipRenewal(renewed);
    }

}

