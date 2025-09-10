package ahmed.test.monolithic.monolithic_mod.students.internal.domain.model;

import java.time.*;
import java.util.Objects;




public final class Membership {
    private final LocalDate issueDate;
    private final LocalDate expiryDate;

    public Membership(LocalDate issueDate, LocalDate expiryDate) {
        this.issueDate = Objects.requireNonNull(issueDate, "issueDate");
        this.expiryDate = Objects.requireNonNull(expiryDate, "expiryDate");
        if (!expiryDate.isAfter(issueDate)) {
            throw new IllegalArgumentException("expiryDate must be after issueDate");
        }
    }

    public LocalDate issueDate()  { return issueDate; }
    public LocalDate expiryDate() { return expiryDate; }
}