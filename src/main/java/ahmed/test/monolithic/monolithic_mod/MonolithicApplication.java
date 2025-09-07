package ahmed.test.monolithic.monolithic_mod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.modulith.core.ApplicationModules;




@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = MonolithicApplication.class)
public class MonolithicApplication {

	public static void main(String[] args) {
        ApplicationModules.of(MonolithicApplication.class).verify();
        SpringApplication.run(MonolithicApplication.class, args);
	}

}
