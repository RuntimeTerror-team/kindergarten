package lt.vtmc.kindergarten.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
        return new
                org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256");
    }
}
