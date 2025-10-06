package ahmed.test.monolithic.monolithic_mod.students.internal.domain.services;



import ahmed.test.monolithic.monolithic_mod.shared.domain.model.AppMessage;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.IBaseBusinessRules;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.User;
import ahmed.test.monolithic.monolithic_mod.shared.domain.services.BaseUseCase;
import ahmed.test.monolithic.monolithic_mod.shared.exception.BusinessRuleException;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Membership;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.services.business_rules.COMBR01MembershipAlreadyIssued;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MembershipIssuance extends BaseUseCase {

       public LocalDate issue(Student student, Clock clock, Period term, User user,
                              List<IBaseBusinessRules> baseBusinessRules) {

           Optional<List<AppMessage>> messages =  validateServiceBR(student, user, Arrays.asList(
                   COMBR01MembershipAlreadyIssued.BUSINESS_RULE_CODE_COM_BR_01), baseBusinessRules );
           if (messages.isPresent() && messages.get().size() > 0) {
               throw new BusinessRuleException("validation exception", null, messages);

           }
           LocalDate today = LocalDate.now(clock);
           Membership issued = new Membership(today, today.plus(term));
           return student.applyMembershipIssuance(issued);
       }



}
