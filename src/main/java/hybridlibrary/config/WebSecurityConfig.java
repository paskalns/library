package hybridlibrary.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users", "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/books", "/books/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
        .and().csrf().disable();
    }

}
