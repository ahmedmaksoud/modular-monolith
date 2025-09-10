package ahmed.test.monolithic.monolithic_mod.students.internal.domain.services;

import java.time.LocalDate;

public class ExtendFromMaxPolicy  {
    public LocalDate baseDateForRenewal(LocalDate today, LocalDate currentExpiry) {
        return (today.isAfter(currentExpiry)) ? today : currentExpiry;
    }
}