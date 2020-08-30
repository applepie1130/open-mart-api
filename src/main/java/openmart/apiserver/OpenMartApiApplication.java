package openmart.apiserver;

import openmart.apiserver.configuration.AppConfig;
import openmart.apiserver.configuration.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = BasePackageLocation.class)
public class OpenMartApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenMartApiApplication.class, args);
	}

}
