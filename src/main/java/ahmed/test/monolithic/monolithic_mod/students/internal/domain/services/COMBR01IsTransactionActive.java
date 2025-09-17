package ahmed.test.monolithic.monolithic_mod.students.internal.domain.services;

import ahmed.test.monolithic.monolithic_mod.shared.IApplogger;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.AppMessage;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.IBaseBusinessRules;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.User;
import ahmed.test.monolithic.monolithic_mod.shared.infrastructure.AppLoggers;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts.IStudentRepository;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import org.slf4j.ILoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Business Rule framework-agnostic , Spring Bean generated in run time
 */
public class COMBR01IsTransactionActive implements IBaseBusinessRules<Student, List<AppMessage>, User> {
    public static final String BUSINESS_RULE_CODE_COM_BR_01 = "COM_BR_01";
    private final IStudentRepository studentRepository;

    IApplogger log = AppLoggers.get(this.getClass());

    public COMBR01IsTransactionActive(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        log.debug("COMBR01IsTransactionActive  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }


    @Override
    public boolean isNotValid(Student s, User user) {

        return false;
    }

    @Override
    public List<AppMessage> addMessage(Student s, User u) {

        List<AppMessage> messages = new ArrayList<>();
        messages.add(new AppMessage(AppMessage.MessageTypes. ERROR, "BUSINESS_RULE_CODE_COM_BR_01"
                , null, null ,null, null));
        return messages;
    }

    @Override
    public String getBusinessRuleCode() {
        return BUSINESS_RULE_CODE_COM_BR_01;
    }

}