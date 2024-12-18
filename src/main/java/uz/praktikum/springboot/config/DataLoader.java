package uz.praktikum.springboot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.praktikum.springboot.entity.*;
import uz.praktikum.springboot.entity.enumration.EmployeeStatus;
import uz.praktikum.springboot.entity.enumration.EmployeePosition;
import uz.praktikum.springboot.service.*;


@Component
public class DataLoader implements CommandLineRunner {
    private final RoleService roleService;
    private final PassportService passportService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public DataLoader(RoleService roleService, PassportService passportService, EmployeeService employeeService, DepartmentService departmentService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passportService = passportService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Override
    public void run(String... args) throws Exception {
        Role directorRole = roleService.save(new Role("Client", EmployeePosition.DIRECTOR,true, true, true, true, true));

        Role departmentHeadRole = roleService.save(new Role("Sales", EmployeePosition.DIRECTOR, true, true, true, false, true));

        Role employeeRole = roleService.save(new Role("Employee", EmployeePosition.DIRECTOR, true, true, true, false, false));


        Passport passportDirector = passportService.save(new Passport("AA", "123456", "12345678912344", "O'zbek"));

        Passport passportDepartmentHead = passportService.save(new Passport("AB", "123455", "12345678911233", "O'zbek"));

        Passport passportEmployee = passportService.save(new Passport("AC", "123477", "12345678912356", "O'zbek"));


        Employee director = employeeService.save(new Employee(
                "director_admin", "123456",
                "director_firstname", "director_lastname", "director_middlename", 35,
                "Director address", true, passportDirector,
                directorRole));
        Employee departmentHead = employeeService.save(new Employee(
                "department_head_admin", "123456",
                "department_head_firstname", "department_head_lastname", "department_head_middlename", 32,
                "Department_head address", true, passportDepartmentHead,
                departmentHeadRole));
        Employee employee = employeeService.save(new Employee(
                "employee_admin", "123456",
                "employee_firstname", "employee_lastname", "employee_middlename", 25,
                "Employee address", true, passportEmployee,
                employeeRole));

        Department departmentIT = departmentService.save(new Department("IT"));
        Department departmentHR = departmentService.save(new Department("HR"));
        Department departmentSales = departmentService.save(new Department("Sales"));

        employeeService.save(new Employee(departmentIT, EmployeeStatus.ACTIVE, EmployeePosition.EMPLOYEE, 1000f, employee));
        employeeService.save(new Employee(departmentHR, EmployeeStatus.ACTIVE, EmployeePosition.EMPLOYEE, 1500f, departmentHead));
        employeeService.save(new Employee(departmentSales, EmployeeStatus.ACTIVE, EmployeePosition.EMPLOYEE, 2000f, director));
    }
}
