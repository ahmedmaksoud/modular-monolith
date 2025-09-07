package ahmed.test.monolithic.monolithic_mod.subjects.internal.infrastructure.db;

import ahmed.test.monolithic.monolithic_mod.shared.IApplogger;
import ahmed.test.monolithic.monolithic_mod.shared.infrastructure.AppLoggers;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import ahmed.test.monolithic.monolithic_mod.students.shared.events.StudentRegistered;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
class ServTest {
    private final ApplicationEventPublisher events;
    IApplogger iApplogger = AppLoggers.get(ServTest.class);

    ServTest(ApplicationEventPublisher events) {
        this.events = events;
    }
    @ApplicationModuleListener
    public void test1(StudentRegistered studentRegistered) {
        iApplogger.info("test1 listener " + studentRegistered);
    }
    @ApplicationModuleListener
    public void test2(StudentRegistered studentRegistered) {
        iApplogger.info("test2 listener " + studentRegistered);
    }
}
