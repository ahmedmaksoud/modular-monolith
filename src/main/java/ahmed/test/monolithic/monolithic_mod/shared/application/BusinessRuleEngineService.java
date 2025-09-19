package ahmed.test.monolithic.monolithic_mod.shared.application;

import ahmed.test.monolithic.monolithic_mod.shared.IApplogger;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.AppMessage;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.DomainModel;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.IBaseBusinessRules;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.User;
import ahmed.test.monolithic.monolithic_mod.shared.infrastructure.AppLoggers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusinessRuleEngineService {

    IApplogger log = AppLoggers.get(this.getClass());
    private final List<IBaseBusinessRules>  businessRules;


    public BusinessRuleEngineService(List<IBaseBusinessRules> businessRules) {
        this.businessRules = businessRules;
        log.debug("[BusinessRuleEngineService] - [businessRules={}]", businessRules.size());
    }

    public List<IBaseBusinessRules> getBusinessRules() {
        return businessRules;
    }
}
