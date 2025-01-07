package uz.praktikum.springboot.service;

import org.springframework.stereotype.Service;

import uz.praktikum.springboot.entity.Employee;
import uz.praktikum.springboot.repository.DepartmentRepository;
import uz.praktikum.springboot.repository.SalesRepository;
import uz.praktikum.springboot.repository.ClientRepository;
import uz.praktikum.springboot.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    private final SalesRepository salesRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    public StatisticsService(SalesRepository salesRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, ClientRepository clientRepository) {
        this.salesRepository = salesRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
    }

    //Employee statistics with Department
   public List<Object[]> getEmployeeStatisticsWithDepartment() {
        return employeeRepository.groupByCountWithDepartment();
    }

    public List<Employee> getEmployeesWithAge(Integer age) {
        return employeeRepository.findAllByActivated(true)
                .stream()
                .filter(employee -> Objects.equals(age, employee.getPassport().getAge()))
                .collect(Collectors.toList());
    }

    public Float getSumSalaryByActivatedTrue() {
        return employeeRepository.sumSalaryByActivatedTrue();
    }

    public List<Object[]> getClientStatisticsWithDailyRegistered() {
        return clientRepository.groupByCountWithDailyRegistered();
    }

    public Object[] getClientStatisticsWithMaxEmployeeRegistered() {
        return clientRepository.groupByCountWithMaxEmployeeRegistered();
    }

    public List<Object[]> getClientStatisticsWithTop3EmployeeRegistered() {
        return clientRepository.groupByCountWithTop3EmployeeRegistered();
    }

    public Integer getClientStatisticsByLastMonthRegistered() {
        return clientRepository.countByLastMonthRegistered();
    }

    public Object[] getClientStatisticsByLastMonthRegisteredMaxDay() {
        return clientRepository.groupByCountWithLastMonthRegisteredMaxDay();
    }

    public Object[] sumMaxAmountSalesFindAdsType() {
        return salesRepository.sumMaxAmountSalesFindAdsType();
    }

    public Object[] findEmployeeMostAmountSales() {
        return salesRepository.findEmployeeMostAmountSales();
    }

    public Integer statisticsLastMonthCountSales() {
        return salesRepository.statisticsLastMonthCountSales();
    }

    public Integer statisticsLastMonthCountEndedSales() {
        return salesRepository.statisticsLastMonthCountEndedSales();
    }

    public List<Object[]> statisticsAdsTypeCountSales() {
        return salesRepository.statisticsAdsTypeCountSales();
    }

}
