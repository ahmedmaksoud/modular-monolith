package ahmed.test.monolithic.monolithic_mod.students.internal.application.membership;

import ahmed.test.monolithic.monolithic_mod.shared.exception.AlreadyIssuedMemberShip;
import ahmed.test.monolithic.monolithic_mod.shared.exception.NotFoundException;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts.IStudentRepository;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentId;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Membership;

import ahmed.test.monolithic.monolithic_mod.students.shared.events.MembershipIssued;
import ahmed.test.monolithic.monolithic_mod.students.shared.events.StudentEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssueMembershipServiceTest {

    @Mock
    IStudentRepository repo;

    @Mock
    ApplicationEventPublisher events;

    @InjectMocks
    IssueMembershipService service;

    @Mock
    Student student;

    @Captor
    ArgumentCaptor<Student> studentCaptor;

//    @BeforeEach
//    void baseStubs() {
//        // Default: repo returns a student
//        when(repo.findByStudentId(any(StudentId.class))).thenReturn(Optional.of(student));
//        // Default: no current membership yet
//        when(student.getMembership()).thenReturn(Optional.empty());
//    }

    @Test
    void issueMembership_happyPath_savesAndPublishes_andReturnsExpiry() {

        Integer sid = 42;
        Student s = mock(Student.class);

        // IMPORTANT: the repo returns THIS EXACT mock
        when(repo.findByStudentId(any(StudentId.class))).thenReturn(Optional.of(s));

       // when(s.getStudentId()).thenReturn(new StudentId(sid));
        // Arrange

        IssueMembershipRequest req = new IssueMembershipRequest(sid);

        // We want occurredEvents() to return the domain events the aggregate would raise.
        // We don’t know the exact date in the service (uses system clock),
        // so we keep event assertions loose and only verify they’re forwarded.
        MembershipIssued evt1 = new MembershipIssued(sid, LocalDate.now(), LocalDate.now().plusYears(1));

        when(s.occurredEvents()).thenReturn(List.of(evt1));

        // The issuance flow calls student.applyMembershipIssuance(issued) and expects the expiry date back.
        // Stub it to mimic a 1-year term from "now", keeping it tolerant to local time.
        LocalDate expectedExpiryLowerBound = LocalDate.now().plusMonths(10);
        LocalDate expectedExpiryUpperBound = LocalDate.now().plusYears(2);
        when(s.applyMembershipIssuance(any(Membership.class)))
                .thenAnswer(inv -> {
                    Membership m = inv.getArgument(0, Membership.class);
                    return m.expiryDate(); // return what issuance created
                });

        // Act
        IssueMembershipResponse res = service.issueMemberShip(req);

        // Assert: repo called with new StudentId(req.studentId())
        verify(repo).findByStudentId(new StudentId(sid));
        // Saved the same student
        verify(repo).saveStudent(studentCaptor.capture());
        assertSame(s, studentCaptor.getValue());

        // Events forwarded
        verify(events).publishEvent(evt1);

        // Expiry date is present and roughly ~1 year out
        assertNotNull(res);
        assertNotNull(res.membershipDate());
        assertFalse(res.membershipDate().isBefore(expectedExpiryLowerBound));
        assertFalse(res.membershipDate().isAfter(expectedExpiryUpperBound));
    }

    @Test
    void issueMembership_studentNotFound_throwsNotFound_andNoSideEffects() {
        // Arrange
        when(repo.findByStudentId(any(StudentId.class))).thenReturn(Optional.empty());
        IssueMembershipRequest req = new IssueMembershipRequest(1);

        // Act + Assert
        NotFoundException ex = assertThrows(NotFoundException.class, () -> service.issueMemberShip(req));
        assertTrue(ex.getMessage().contains("Student not found"));

        verify(repo, never()).saveStudent(any());
        verify(events, never()).publishEvent(any());
    }

    @Test
    void issueMembership_alreadyIssued_throwsAlreadyIssuedMemberShip_andNoSideEffects() {
        // Arrange: student already has a membership
        Student s = mock(Student.class);



        // IMPORTANT: the repo returns THIS EXACT mock
        when(repo.findByStudentId(any(StudentId.class))).thenReturn(Optional.of(s));

        // Make the aggregate look "already issued"
        when(s.getMembership()).thenReturn(Optional.of(mock(Membership.class)));

        when(s.getStudentId()).thenReturn(new StudentId(2));

//        when(repo.findByStudentId(any(StudentId.class))).thenReturn(Optional.of(mock(Student.class)));
//        when(student.getMembership()).thenReturn(Optional.of(mock(Membership.class)));
       IssueMembershipRequest req = new IssueMembershipRequest(2);

        // Act + Assert: the domain rule in MembershipIsssuance should throw
        assertThrows(AlreadyIssuedMemberShip.class, () -> service.issueMemberShip(req));

        // No save / publish on failure
        verify(repo, never()).saveStudent(any());
        verify(events, never()).publishEvent(any());
    }
}
