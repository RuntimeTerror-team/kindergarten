package lt.vtmc.kindergarten.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;


@Configuration
public class MigrationConfig {
    @Autowired
    private Environment environment;

    public MigrationConfig() {
    }

    @Autowired
    public MigrationConfig(Environment environment) {
        this.environment=environment;
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
