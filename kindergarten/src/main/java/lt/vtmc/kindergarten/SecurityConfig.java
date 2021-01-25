package lt.vtmc.kindergarten;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityEntryPoint securityEntryPoint;

    @Autowired
    private UserDetailsService userService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // FIXME
        // {noop} is required in order not to encode the password for now. Later we should use encoding
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/", "/**", "/console/**", "/api/**", "/swagger-ui/").permitAll()
                .and().csrf().ignoringAntMatchers("/", "/**", "/console/**", "/api/**", "/swagger-ui/")
                .and().headers().frameOptions().disable();

//        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
//                .authorizeRequests().antMatchers("/console/**").permitAll()
//                .and().csrf().ignoringAntMatchers("/console/**")
//                .and().headers().frameOptions().sameOrigin();

//        httpSecurity.csrf().disable();
//        httpSecurity.headers().frameOptions().disable();

    }
    public void setSecurityEntryPoint(SecurityEntryPoint securityEntryPoint) {
        this.securityEntryPoint = securityEntryPoint;
    }

    public void setUserService(UserDetailsService userService) {
        this.userService = userService;
    }
}
