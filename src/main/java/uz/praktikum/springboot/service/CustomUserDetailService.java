package uz.praktikum.springboot.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.*;
import org.springframework.stereotype.Component;
import uz.praktikum.springboot.entity.Employee;
import uz.praktikum.springboot.repository.EmployeeRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public CustomUserDetailService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByLogin(username);

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> authorities = new ArrayList<>();


                return authorities;

            }

            @Override
            public String getPassword() {
                return employee.getPassword();
            }

            @Override
            public String getUsername() {
                return employee.getLogin();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

        };

    }

}
