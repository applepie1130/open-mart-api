package openmart.apiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import openmart.apiserver.configuration.AppConfig;
import openmart.apiserver.configuration.SwaggerConfig;

@SpringBootApplication
@EnableAutoConfiguration
@Import({AppConfig.class, SwaggerConfig.class})
@ComponentScan(basePackages = "openmart")
public class OpenMartApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenMartApiApplication.class, args);
	}

}
