package uz.praktikum.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF ni o'chirish
                .authorizeHttpRequests(auth -> auth
                        // Clients API access control
                        .requestMatchers(HttpMethod.GET, "/api/clients").hasAuthority("CLIENTS_READ")
                        .requestMatchers(HttpMethod.POST, "/api/clients").hasAuthority("CLIENTS_CREATE")
                        .requestMatchers(HttpMethod.PUT, "/api/clients/**").hasAuthority("CLIENTS_UPDATE")
                        .requestMatchers(HttpMethod.DELETE, "/api/clients/**").hasAuthority("CLIENTS_DELETE")
                        // Employees API access control
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasAuthority("EMPLOYEES_READ")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasAuthority("EMPLOYEES_CREATE")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasAuthority("EMPLOYEES_UPDATE")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasAuthority("EMPLOYEES_DELETE")
                        // Sales API access control
                        .requestMatchers(HttpMethod.GET, "/api/sales").hasAuthority("SALES_READ")
                        .requestMatchers(HttpMethod.POST, "/api/sales").hasAuthority("SALES_CREATE")
                        .requestMatchers(HttpMethod.PUT, "/api/sales/**").hasAuthority("SALES_UPDATE")
                        .requestMatchers(HttpMethod.DELETE, "/api/sales/**").hasAuthority("SALES_DELETE")
                        // Statistics API access control
                        .requestMatchers("/api/statistics/clients/**").hasAuthority("CLIENTS_STATISTICS")
                        .requestMatchers("/api/statistics/employees/**").hasAuthority("EMPLOYEES_STATISTICS")
                        .requestMatchers("/api/statistics/sales/**").hasAuthority("SALES_STATISTICS")
                        .anyRequest().authenticated()
                ).formLogin(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
