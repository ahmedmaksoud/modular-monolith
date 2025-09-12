package ahmed.test.monolithic.monolithic_mod.students.internal.domain.services;

import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Membership;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentProp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class MembershipRenewalTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testRenewal() {
        Membership membership = new Membership(LocalDate.now(), LocalDate.now().plusYears(1));

        Student student =  Student.create(new StudentProp(
                100,
                "Ahmed", "Mak", null, membership
        ));

        Clock clock = Clock.systemDefaultZone();
        LocalDate today = LocalDate.now(clock);
        Period defaultTerm = Period.ofMonths(12);

        ExtendFromMaxPolicy renewalPolicy = new ExtendFromMaxPolicy();
        MembershipRenewal membershipRenewal =  new MembershipRenewal(renewalPolicy);


        LocalDate expDate =  membershipRenewal.renew(student, clock, defaultTerm);

        assertNotNull(expDate, "expDate should not be null");
        assertEquals(expDate, LocalDate.now().plusYears(2) );

    }
}