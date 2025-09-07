package ahmed.test.monolithic.monolithic_mod.students.shared.events;

import ahmed.test.monolithic.monolithic_mod.shared.domain.model.DomainEvent;

public sealed interface StudentEvent extends DomainEvent permits
        StudentRegistered {
}
