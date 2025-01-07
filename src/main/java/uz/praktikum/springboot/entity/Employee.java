package uz.praktikum.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import uz.praktikum.springboot.entity.enumration.EmployeePosition;
import uz.praktikum.springboot.entity.enumration.EmployeeStatus;
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Employee(String login, String password, Boolean activated, Department department, EmployeeStatus employeeStatus, EmployeePosition employeePosition, Float salary, Passport passport, Set<Role> roles) {
        this.login = login;
        this.password = password;
        this.activated = activated;
        this.department = department;
        this.employeeStatus = employeeStatus;
        this.employeePosition = employeePosition;
        this.salary = salary;
        this.passport = passport;
        this.roles = roles;
    }

    @NotNull
    @Size(min = 4, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;


    @NotNull
    @Size(min = 20, max = 60)
    @Column(length = 60, unique = true, nullable = false)
    private String password;


    @NotNull
    @Column(nullable = false)
    private Boolean activated = false;

    @ManyToOne
    private Department department;

    private EmployeeStatus employeeStatus;

    private EmployeePosition employeePosition;

    private Float salary;

    @OneToOne
    @JoinColumn(name = "passport_id", unique = true, nullable = false)
    private Passport passport;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_role",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<>();


    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public @NotNull @Size(min = 4, max = 50) String getLogin() {
        return login;
    }

    public void setLogin(@NotNull @Size(min = 4, max = 50) String login) {
        this.login = login;
    }

    @JsonIgnore
    public @NotNull @Size(min = 20, max = 60) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 20, max = 60) String password) {
        this.password = password;
    }

    public @NotNull Boolean getActivated() {
        return activated;
    }

    public void setActivated(@NotNull Boolean activated) {
        this.activated = activated;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public EmployeePosition getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(EmployeePosition employeePosition) {
        this.employeePosition = employeePosition;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}
