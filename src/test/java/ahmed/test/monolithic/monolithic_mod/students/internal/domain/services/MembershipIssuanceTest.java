package ahmed.test.monolithic.monolithic_mod.students.internal.domain.services;

import ahmed.test.monolithic.monolithic_mod.shared.domain.model.IBaseBusinessRules;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.User;
import ahmed.test.monolithic.monolithic_mod.shared.exception.AlreadyIssuedMemberShip;
import ahmed.test.monolithic.monolithic_mod.shared.exception.BusinessRuleException;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Membership;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentProp;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.services.businnes_rules.COMBR01MembershipAlreadyIssued;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

 class MembershipIssuanceTest {

    @Test
    void testIssueAlreadyIssued() {
        Membership membership = new Membership(LocalDate.now(), LocalDate.now().plusYears(1));

        Student student =  Student.create(new StudentProp(
                100,
                "Ahmed", "Mak", null, membership
        ));
        User user = new User();

        Clock clock = Clock.systemDefaultZone();
        LocalDate today = LocalDate.now(clock);
        Period defaultTerm = Period.ofMonths(12);

        ExtendFromMaxPolicy renewalPolicy = new ExtendFromMaxPolicy();
        MembershipIssuance membershipIssuance =  new MembershipIssuance();

        List<IBaseBusinessRules> rules = Collections.singletonList(new COMBR01MembershipAlreadyIssued(null));

        assertThrows(BusinessRuleException.class, () -> {
           membershipIssuance.issue(student, clock, defaultTerm,user,  rules);
        });

    }


    @Test
    void testIssue() {
        Student student =  Student.create(new StudentProp(
                100,
                "Ahmed", "Mak", null, null
        ));
        User user = new User();
        Clock clock = Clock.systemDefaultZone();
        LocalDate today = LocalDate.now(clock);
        Period defaultTerm = Period.ofMonths(12);

        ExtendFromMaxPolicy renewalPolicy = new ExtendFromMaxPolicy();
        MembershipIssuance membershipIssuance =  new MembershipIssuance();


        LocalDate expDate =  membershipIssuance .issue(student, clock, defaultTerm,user, null);

        assertNotNull(expDate, "expDate should not be null");
        assertEquals(expDate, LocalDate.now().plusYears(1) );

    }

}