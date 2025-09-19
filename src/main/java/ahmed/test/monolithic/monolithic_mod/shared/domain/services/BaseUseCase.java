package ahmed.test.monolithic.monolithic_mod.shared.domain.services;

import ahmed.test.monolithic.monolithic_mod.shared.domain.model.AppMessage;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.DomainModel;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.IBaseBusinessRules;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BaseUseCase {
    /***
     *
     * @param domainModel :: Business domain model object
     * @param user :: logged-in user
     * @param businessRuleCodes ::
     * @return
     */
    public Optional<List<AppMessage>> validateServiceBR(DomainModel domainModel, User user, List<String> businessRuleCodes,
                                                        List<IBaseBusinessRules> businessRules) {

        //populatedServiceDTO.setLang(user != null && User.LANGS.ARABIC.equals(user.getLang()) ? LANGS_CODE.AR.toString() : LANGS_CODE.EN.toString());
        if (businessRules == null || businessRules.isEmpty()) return Optional.empty();
        List<AppMessage> messages = businessRules.stream()
                .filter(br -> businessRuleCodes.contains(br.getBusinessRuleCode()))
                .filter(br -> ((IBaseBusinessRules<DomainModel,List<AppMessage>, User>) br).evaluate(domainModel, user))
                .map(br -> ((IBaseBusinessRules<DomainModel,List<AppMessage>, User>) br).addMessage(domainModel, user))
                .flatMap(List::stream).collect(Collectors.toList());

        return Optional.of(messages);
    }


}
