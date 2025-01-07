package uz.praktikum.springboot.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.praktikum.springboot.entity.Employee;
import uz.praktikum.springboot.entity.enumration.EmployeePosition;
import uz.praktikum.springboot.entity.enumration.EmployeeStatus;
import uz.praktikum.springboot.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee save( Employee employee) {
        if (
                !employeeRepository.existsByLogin(employee.getLogin())
        ) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            return employeeRepository.save(employee);
        }
        return employeeRepository.findByLogin(employee.getLogin());
    }

    public List<Employee> findAll(Authentication authentication) {

        if (getAccess(authentication)) {
            return employeeRepository.findAllByEmployeeStatus(EmployeeStatus.ACTIVE);
        } else {
            return employeeRepository.findAllByLogin(authentication.getName());
        }
    }

    public Boolean getAccess(Authentication authentication) {
        Employee employee = getEmployee(authentication);
        return employee.getDepartment().getName().equalsIgnoreCase("HR") ||
                employee.getEmployeePosition().equals(EmployeePosition.DEPARTMENT_HEAD) ||
                employee.getEmployeePosition().equals(EmployeePosition.DIRECTOR);
    }

    public Employee findById(Authentication authentication, Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent() && getAccess(authentication)) {
            return optional.get();
        }
        return null;
    }

    public Employee getEmployee(Authentication authentication) {
        return employeeRepository.findByLogin(authentication.getName());
    }


    public void delete(Authentication authentication, Long id) {
        if (getAccess(authentication)) {
            if(getEmployee(authentication).getEmployeePosition()==EmployeePosition.DIRECTOR){
                employeeRepository.deleteById(id);
            }else {
                Employee employee = employeeRepository.findById(id).orElseThrow();
                employee.setEmployeeStatus(EmployeeStatus.DRAFT);
                save(employee);
            }
        }
    }
}
