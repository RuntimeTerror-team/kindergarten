package lt.vtmc.kindergarten;


import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
public class KindergartenApplication extends SpringBootServletInitializer {
	private static final Logger logger
			= (Logger) LoggerFactory.getLogger(KindergartenApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KindergartenApplication.class, args);
		logger.info("App started");
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(KindergartenApplication.class);
	}

	@Bean
	public Docket swaggerDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("lt.vtmc.kindergarten"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("REST Documentation").version("0.0.1-SNAPSHOT").build();
	}
}
