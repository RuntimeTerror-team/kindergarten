package lt.vtmc.kindergarten.security;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class PepperedBCryptPasswordEncoder extends BCryptPasswordEncoder {

    @Autowired
    Environment environment;

    private String pepper;

    public PepperedBCryptPasswordEncoder() {
        super(10);
    }

    @PostConstruct
    public void init(){
        this.pepper = environment.getProperty("security.pepper");
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return super.encode(rawPassword + pepper);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return super.matches(rawPassword + pepper, encodedPassword);
    }
}

