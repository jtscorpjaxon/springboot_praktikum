package uz.praktikum.springboot.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

import org.springframework.stereotype.Component;
import uz.praktikum.springboot.entity.Employee;
import uz.praktikum.springboot.entity.Role;
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
                Set<Role> role = employee.getRoles();
                for (Role role_item : role) {
                    String roleName = role_item.getApiName().toUpperCase();
                    if (role_item.isCanRead()) {
                        authorities.add(new SimpleGrantedAuthority(roleName + "_READ"));
                    }
                    if (role_item.isCanCreate()) {
                        authorities.add(new SimpleGrantedAuthority(roleName + "_CREATE"));
                    }
                    if (role_item.isCanUpdate()) {
                        authorities.add(new SimpleGrantedAuthority(roleName + "_UPDATE"));
                    }
                    if (role_item.isCanDelete()) {
                        authorities.add(new SimpleGrantedAuthority(roleName + "_DELETE"));
                    }

                    if (role_item.isHasStatisticsAccess()) {
                        authorities.add(new SimpleGrantedAuthority(roleName + "_STATISTICS"));
                    }
                }
                System.out.println("Authorities: " + authorities);
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
