package belleza.studio.app.config;

import belleza.studio.app.services.impl.BellezaDBUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BellezaDBUserService bellezaDBUserService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(BellezaDBUserService bellezaDBUserService, PasswordEncoder passwordEncoder) {
        this.bellezaDBUserService = bellezaDBUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/img/**").permitAll()
                .antMatchers( "/", "/users/login", "/booking/hour", "/gallery").permitAll()
                .antMatchers("/admin-panel", "/add-user").hasRole("ADMIN")
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home", true)
                .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                // which endpoint performs logout, e.g. http://localhost:8000/logout (!this should be POST request)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // where to land after logout
                    .logoutSuccessUrl("/")
                // remove the session from the server
                    .invalidateHttpSession(true)
                // delete the session cookie
                    .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(bellezaDBUserService)
                .passwordEncoder(passwordEncoder);
    }
}
