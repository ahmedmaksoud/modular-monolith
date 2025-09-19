package ahmed.test.monolithic.monolithic_mod.shared.exception;

import ahmed.test.monolithic.monolithic_mod.shared.domain.model.AppMessage;

import java.util.List;
import java.util.Optional;

public class BusinessRuleException extends AppException {
    public BusinessRuleException(String validationExceptoin, Exception e, Optional<List<AppMessage>> messages) {

        super(validationExceptoin, e, messages);

    }
}
