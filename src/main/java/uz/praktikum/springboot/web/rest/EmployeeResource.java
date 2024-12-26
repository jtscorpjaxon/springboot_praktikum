package uz.praktikum.springboot.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.praktikum.springboot.entity.Employee;
import uz.praktikum.springboot.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity getAll(Authentication authentication) {
        List<Employee> result = employeeService.findAll(authentication);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/employees")
    public ResponseEntity create(Authentication authentication,
                                 @RequestBody Employee employee) {
        if (employeeService.getAccess(authentication)) {
            Employee result = employeeService.save(employee);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: You do not have the required role.");
    }

    @PutMapping("/employees")
    public ResponseEntity update(Authentication authentication,
                                 @RequestBody Employee employee) {
        if (employeeService.getAccess(authentication)) {
            if (employee.getId() == null) {
                return ResponseEntity.badRequest().build();
            }
            Employee result = employeeService.save(employee);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: You do not have the required role.");

    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getOne(Authentication authentication,
                                           @PathVariable Long id) {
        Employee result = employeeService.findById(authentication, id);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/employees/{id}")
    public ResponseEntity delete(Authentication authentication, @PathVariable Long id) {
        employeeService.delete(authentication, id);
        return ResponseEntity.ok("O'chdi");

    }
}
