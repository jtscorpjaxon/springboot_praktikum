package uz.praktikum.springboot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.praktikum.springboot.entity.*;
import uz.praktikum.springboot.entity.enumration.EmployeePosition;
import uz.praktikum.springboot.entity.enumration.EmployeeStatus;
import uz.praktikum.springboot.service.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Component
public class DataLoader implements CommandLineRunner {
    private final RoleService roleService;
    private final PassportService passportService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public DataLoader(RoleService roleService, PassportService passportService, EmployeeService employeeService, DepartmentService departmentService) {
        this.roleService = roleService;
        this.passportService = passportService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Override
    public void run(String... args) throws Exception {

        Role clientRole = roleService.save(new Role("Clients", true, true, true, true, true));
        Role salesRole = roleService.save(new Role("Sales", true, true, true, true, true));
        Role employeeRole = roleService.save(new Role("Employees", true, true, true, true, true));

        Role clientCRU = roleService.save(new Role("Clients", true, true, true, false, false));
        Role salesCRU = roleService.save(new Role("Sales", true, true, true, false, false));
        Role employeeCRU = roleService.save(new Role("Employees", true, true, true, false, false));



        Set<Role> directorRoles = new HashSet<>();
        directorRoles.add(clientRole);
        directorRoles.add(salesRole);
        directorRoles.add(employeeRole);

        Set<Role> departmentHeadRoles = new HashSet<>();
        directorRoles.add(clientRole);
        directorRoles.add(salesRole);
        departmentHeadRoles.add(employeeCRU);

        Set<Role> employeeRoles = new HashSet<>();
        employeeRoles.add(clientCRU);
        employeeRoles.add(salesCRU);
        employeeRoles.add(employeeCRU);

        Set<Role> salesRoles = new HashSet<>();
        salesRoles.add(clientCRU);
        salesRoles.add(salesCRU);
        salesRoles.add(employeeCRU);


        Passport passportDirector = passportService.save(
                new Passport("director_firstname", "director_lastname", "director_middlename", LocalDate.of(1980, 8, 12),
                        "Director address", "AA", "123456", "12345678912344", "O'zbek"));
        Passport passportDepartmentHead = passportService.save(
                new Passport("department_head_firstname", "department_head_lastname", "department_head_middlename", LocalDate.of(1992, 2, 2),
                        "Department_head address", "AB", "123455", "12345678911233", "O'zbek"));
        Passport passportEmployee = passportService.save(
                new Passport("employee_firstname", "employee_lastname", "employee_middlename", LocalDate.of(1996, 9, 23),
                        "Employee address", "AC", "123477", "12345678912356", "O'zbek"));
        Passport passportSales = passportService.save(
                new Passport("sales_firstname", "sales_lastname", "sales_middlename", LocalDate.of(2002, 2, 2),
                        "Sales address", "AD", "123477", "12345678912357", "O'zbek"));

        Department departmentIT = departmentService.save(new Department("IT"));
        Department departmentHR = departmentService.save(new Department("HR"));
        Department departmentClients = departmentService.save(new Department("Clients"));
        Department departmentSales = departmentService.save(new Department("Sales"));

        Employee director = employeeService.save(new Employee(
                "director_admin", "123456",
                true, departmentIT, EmployeeStatus.ACTIVE, EmployeePosition.DIRECTOR,
                2000f, passportDirector, directorRoles));
        Employee departmentHead = employeeService.save(new Employee(
                "department_head_admin", "123456",
                true, departmentHR, EmployeeStatus.ACTIVE, EmployeePosition.DEPARTMENT_HEAD,
                1500f, passportDepartmentHead, departmentHeadRoles));

        Employee employee = employeeService.save(new Employee(
                "client_admin", "123456",
                true, departmentClients, EmployeeStatus.ACTIVE, EmployeePosition.EMPLOYEE,
                1000f, passportEmployee, employeeRoles));
      Employee sales = employeeService.save(new Employee(
                "sales_admin", "123456",
                true, departmentSales, EmployeeStatus.ACTIVE, EmployeePosition.EMPLOYEE,
                1000f, passportSales, salesRoles));
/*
        employeeService.save(director);
        employeeService.save(departmentHead);
        employeeService.save(employee);
      employeeService.save(sales);*/
    }
}
