package uz.praktikum.springboot.repository;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.praktikum.springboot.entity.Department;
import uz.praktikum.springboot.entity.Employee;
import uz.praktikum.springboot.entity.enumration.EmployeePosition;
import uz.praktikum.springboot.entity.enumration.EmployeeStatus;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentId(Long id);
    List<Employee> findAllByEmployeeStatus(EmployeeStatus status);
    List<Employee> findAllByActivated(Boolean activated);
    Employee findByLogin(String login);
    boolean existsByLogin(String login);

    Float sumSalaryByActivatedTrue();

   // Group by count with department
    @Query("select e.department.name, count(e) from Employee e group by e.department")
    List<Object[]> groupByCountWithDepartment();


}
