package lt.vtmc.kindergarten.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class MigrationConfig {
    @Autowired
    private Environment environment;

    public MigrationConfig() {
    }

    @Autowired
    public void flywayConfig() {
        Flyway flyway = Flyway
                .configure()
                .baselineOnMigrate(true)
                .dataSource(
                        environment.getProperty("spring.datasource.url"),
                        environment.getProperty("spring.datasource.username"),
                        environment.getProperty("spring.datasource.password"))
                .load();
        flyway.migrate();
    }
}
