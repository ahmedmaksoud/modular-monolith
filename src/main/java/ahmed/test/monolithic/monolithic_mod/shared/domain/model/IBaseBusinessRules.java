package ahmed.test.monolithic.monolithic_mod.shared.domain.model;

import java.util.List;

public interface IBaseBusinessRules<T extends DomainModel,
        C extends List<? extends AppMessage>, U extends User> {
    public boolean isNotValid(T i, User user);

    public List<AppMessage> addMessage(T i, User userDTO);

    public String getBusinessRuleCode();
}