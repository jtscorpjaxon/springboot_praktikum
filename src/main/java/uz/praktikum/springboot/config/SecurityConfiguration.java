package uz.praktikum.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                // Clients  API access control
                .antMatchers(HttpMethod.GET, "/api/clients").hasAuthority("CLIENTS_READ")
                .antMatchers(HttpMethod.POST, "/api/clients").hasAuthority("CLIENTS_CREATE")
                .antMatchers(HttpMethod.PUT, "/api/clients/**").hasAuthority("CLIENTS_UPDATE")
                .antMatchers(HttpMethod.DELETE, "/api/clients/**").hasAuthority("CLIENTS_DELETE")
                // Employees API access control
                .antMatchers(HttpMethod.GET, "/api/employees").hasAuthority("EMPLOYEES_READ")
                .antMatchers(HttpMethod.POST, "/api/employees").hasAuthority("EMPLOYEES_CREATE")
                .antMatchers(HttpMethod.PUT, "/api/employees/**").hasAuthority("EMPLOYEES_UPDATE")
                .antMatchers(HttpMethod.DELETE, "/api/employees/**").hasAuthority("EMPLOYEES_DELETE")
                // Sales API access control
                .antMatchers(HttpMethod.GET, "/api/sales").hasAuthority("SALES_READ")
                .antMatchers(HttpMethod.POST, "/api/sales").hasAuthority("SALES_CREATE")
                .antMatchers(HttpMethod.PUT, "/api/sales/**").hasAuthority("SALES_UPDATE")
                .antMatchers(HttpMethod.DELETE, "/api/sales/**").hasAuthority("SALES_DELETE")
                // Statistics API access control
                .antMatchers("/api/statistics/clients").hasAuthority("CLIENTS_STATISTICS")
                .antMatchers("/api/statistics/employees").hasAuthority("EMPLOYEES_STATISTICS")
                .antMatchers("/api/statistics/sales").hasAuthority("SALES_STATISTICS")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
