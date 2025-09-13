package ahmed.test.monolithic.monolithic_mod.teachers.test;

import ahmed.test.monolithic.monolithic_mod.shared.IApplogger;
import ahmed.test.monolithic.monolithic_mod.shared.infrastructure.AppLoggers;
import ahmed.test.monolithic.monolithic_mod.students.shared.events.StudentRegistered;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class TestEventListener {
    private final ApplicationEventPublisher events;
    IApplogger iApplogger = AppLoggers.get(TestEventListener.class);

    TestEventListener(ApplicationEventPublisher events) {

        this.events = events;
    }

    //test roll back if some exception happen
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void test1(StudentRegistered studentRegistered) {
        iApplogger.info("test1 listener " + studentRegistered);
       // throw new RuntimeException("dddddddddddddddddddddddddddddddddddddddd");

    }
    @ApplicationModuleListener
    public void test2(StudentRegistered studentRegistered) {
        iApplogger.info("test2 listener " + studentRegistered);
    }
}
