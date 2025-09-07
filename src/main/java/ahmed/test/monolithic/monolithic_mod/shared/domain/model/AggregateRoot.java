package ahmed.test.monolithic.monolithic_mod.shared.domain.model;

import ahmed.test.monolithic.monolithic_mod.shared.IApplogger;
import ahmed.test.monolithic.monolithic_mod.shared.infrastructure.AppLoggers;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot<T extends ValueObject> extends Entity<T> {
    private static final IApplogger log = AppLoggers.get(AggregateRoot.class);


    private final List<DomainEvent> occurredEvents = new ArrayList<>();

    protected AggregateRoot(T id) {
        super(id);
    }

    public List<DomainEvent> occurredEvents() {
        List<DomainEvent> events = new ArrayList<>(this.occurredEvents);
        this.occurredEvents.clear();
        log.trace("Return occurred domain events. [numberOfEvents={}]", events.size());
        return events;
    }

    protected void raiseEvent(DomainEvent event) {
        occurredEvents.add(event);
        log.debug("Raised new domain event. [type={}]", event.getClass().getSimpleName());
    }
}