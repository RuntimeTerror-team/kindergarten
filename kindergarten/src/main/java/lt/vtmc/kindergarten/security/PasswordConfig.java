package lt.vtmc.kindergarten.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new
//                MessageDigestPasswordEncoder("SHA-256");
        return new BCryptPasswordEncoder();
    }
}
