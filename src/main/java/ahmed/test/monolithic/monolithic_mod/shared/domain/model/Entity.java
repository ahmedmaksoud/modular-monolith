package ahmed.test.monolithic.monolithic_mod.shared.domain.model;

import ahmed.test.monolithic.monolithic_mod.shared.IApplogger;
import ahmed.test.monolithic.monolithic_mod.shared.infrastructure.AppLoggers;

public abstract class Entity<T extends ValueObject> implements DomainModel {
    private static final IApplogger log = AppLoggers.get(Entity.class);
    private final T id;

    protected Entity(T id) {
        this.id = id;
        //this.log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    }
    public T getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Entity<?> otherEntity = (Entity<?>) obj;
        return this.id.equals(otherEntity.getId());
    }
}