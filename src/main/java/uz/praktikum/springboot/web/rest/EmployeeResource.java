package uz.praktikum.springboot.web.rest;

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
    public ResponseEntity getAll(Authentication authentication){
        List <Employee> result = employeeService.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/employees")
    public ResponseEntity create(@RequestBody Employee employee){
        Employee result= employeeService.save(employee);
        return ResponseEntity.ok(result);
    }
    @PutMapping("/employees")
    public ResponseEntity update(@RequestBody Employee employee){
        if(employee.getId()==null){
            return ResponseEntity.badRequest().build();
        }
        Employee result= employeeService.save(employee);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getOne(@PathVariable Long id){
        Employee result = employeeService.findById(id);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/employees/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok("O'chdi");

    }
}
