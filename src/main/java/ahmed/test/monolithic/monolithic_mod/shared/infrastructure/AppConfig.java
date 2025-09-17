package ahmed.test.monolithic.monolithic_mod.shared.infrastructure;


import ahmed.test.monolithic.monolithic_mod.shared.domain.model.IBaseBusinessRules;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * search for all classes and auto bean generated
 */
@Configuration
@ComponentScan(
    basePackages = "ahmed.test.monolithic.monolithic_mod",
    useDefaultFilters = false,
    includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = IBaseBusinessRules.class
    )
)
public class AppConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:locale/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}