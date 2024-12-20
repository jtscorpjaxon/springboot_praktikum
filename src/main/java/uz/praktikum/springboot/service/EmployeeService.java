package uz.praktikum.springboot.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.praktikum.springboot.entity.Employee;
import uz.praktikum.springboot.entity.enumration.EmployeeStatus;
import uz.praktikum.springboot.repository.EmployeeRepository;

import javax.persistence.EntityNotFoundException;
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

    public Employee save(Employee employee) {
        if (!employeeRepository.existsByLogin(employee.getLogin())){
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            return employeeRepository.save(employee);
        }
        return employeeRepository.findByLogin(employee.getLogin());
    }

    public List<Employee> findAll() {
        return employeeRepository.findAllByEmployeeStatus(EmployeeStatus.ACTIVE);
    }

    public Employee findById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }


    public void delete(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        employee.setEmployeeStatus(EmployeeStatus.DRAFT);
        save(employee);
    }
}
