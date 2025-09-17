package ahmed.test.monolithic.monolithic_mod.shared.infrastructure;


import ahmed.test.monolithic.monolithic_mod.shared.domain.model.IBaseBusinessRules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = "ahmed.test.monolithic.monolithic_mod", // <- change to your root package
    useDefaultFilters = false,
    includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = IBaseBusinessRules.class
    )
)
public class RulesConfig { }