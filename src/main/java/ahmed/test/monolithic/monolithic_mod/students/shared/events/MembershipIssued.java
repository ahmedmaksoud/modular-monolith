package ahmed.test.monolithic.monolithic_mod.students.shared.events;

import ahmed.test.monolithic.monolithic_mod.shared.domain.model.DomainEvent;

import java.time.LocalDate;

public record MembershipIssued(Integer studentId, LocalDate issueDate, LocalDate expiryDate) implements StudentEvent {}

