package ahmed.test.monolithic.monolithic_mod.students.shared.events;

import java.time.LocalDate;

public record MembershipRenewed(Integer studentId, LocalDate issueDate, LocalDate expiryDate) implements StudentEvent {}