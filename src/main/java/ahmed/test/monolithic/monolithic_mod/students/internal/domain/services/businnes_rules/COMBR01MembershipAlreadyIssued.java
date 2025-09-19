package ahmed.test.monolithic.monolithic_mod.students.internal.domain.services.businnes_rules;

import ahmed.test.monolithic.monolithic_mod.shared.IApplogger;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.AppMessage;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.IBaseBusinessRules;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.User;
import ahmed.test.monolithic.monolithic_mod.shared.infrastructure.AppLoggers;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts.IStudentRepository;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Membership;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Business Rule framework-agnostic , Spring Bean generated in run time
 */
public class COMBR01MembershipAlreadyIssued implements IBaseBusinessRules<Student, List<AppMessage>, User> {
    public static final String BUSINESS_RULE_CODE_COM_BR_01 = "COM_BR_01";
    private final IStudentRepository studentRepository;

    IApplogger log = AppLoggers.get(this.getClass());

    public COMBR01MembershipAlreadyIssued(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        log.debug("COMBR01MembershipAlreadyIssued  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }


    @Override
    public boolean evaluate(Student student, User user) {
        log.debug("COMBR01MembershipAlreadyIssued evaluate");
        Optional<Membership> current = student.getMembership();
        if (current.isPresent()) {
            return true;
        }
       return  false;
    }

    @Override
    public List<AppMessage> addMessage(Student s, User u) {
        log.debug("COMBR01MembershipAlreadyIssued addMessage");
        String messageKey = "business.rule.test";
        List<AppMessage> messages = new ArrayList<>();
        messages.add(new AppMessage(AppMessage.MessageTypes. ERROR, messageKey
                , null, null ,null, null));
        return messages;
    }

    @Override
    public String getBusinessRuleCode() {
        return BUSINESS_RULE_CODE_COM_BR_01;
    }

}