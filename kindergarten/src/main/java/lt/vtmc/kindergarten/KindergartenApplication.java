package lt.vtmc.kindergarten;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
public class KindergartenApplication {

    private static final Logger logger
            = (Logger) LoggerFactory.getLogger(KindergartenApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KindergartenApplication.class, args);
        logger.info("App started");
    }
}
