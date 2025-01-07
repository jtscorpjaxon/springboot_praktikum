package uz.praktikum.springboot.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.praktikum.springboot.entity.Employee;
import uz.praktikum.springboot.entity.Sales;
import uz.praktikum.springboot.service.StatisticsService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatisticsResource {

    private final StatisticsService statisticsService;


    public StatisticsResource(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/statistics/employee")
    public ResponseEntity<List<Object[]>> getEmployeeStatisticsWithDepartment() {
        return ResponseEntity.ok(statisticsService.getEmployeeStatisticsWithDepartment());
    }

    @GetMapping("/statistics/employee/age/{age}")
    public ResponseEntity<List<Employee>> getEmployeesWithAge(@PathVariable Integer age) {
        return ResponseEntity.ok(statisticsService.getEmployeesWithAge(age));
    }

    @GetMapping("/statistics/employee/salary")
    public ResponseEntity<Float> getSumSalaryByActivatedTrue() {
        return ResponseEntity.ok(statisticsService.getSumSalaryByActivatedTrue());
    }

    @GetMapping("/statistics/client/daily-registered")
    public ResponseEntity<List<Object[]>> getClientStatisticsWithDailyRegistered() {
        return ResponseEntity.ok(statisticsService.getClientStatisticsWithDailyRegistered());
    }

    @GetMapping("/statistics/client/max-employee-registered")
    public ResponseEntity<Object[]> getClientStatisticsWithMaxEmployeeRegistered() {
        return ResponseEntity.ok(statisticsService.getClientStatisticsWithMaxEmployeeRegistered());
    }

    @GetMapping("/statistics/client/top3-employee-registered")
    public ResponseEntity<List<Object[]>> getClientStatisticsWithTop3EmployeeRegistered() {
        return ResponseEntity.ok(statisticsService.getClientStatisticsWithTop3EmployeeRegistered());
    }

    @GetMapping("/statistics/client/last-month-registered")
    public ResponseEntity<Integer> getClientStatisticsByLastMonthRegistered() {
        return ResponseEntity.ok(statisticsService.getClientStatisticsByLastMonthRegistered());
    }

    @GetMapping("/statistics/sales/sum-max-amount-ads-type")
    public ResponseEntity<Object[]> sumMaxAmountSalesFindAdsType() {
        return ResponseEntity.ok(statisticsService.sumMaxAmountSalesFindAdsType());
    }

    @GetMapping("/statistics/sales/employee-most-amount-sales")
    public ResponseEntity<Object[]> findEmployeeMostAmountSales() {
        return ResponseEntity.ok(statisticsService.findEmployeeMostAmountSales());
    }

    @GetMapping("/statistics/sales/last-month-count-sales")
    public ResponseEntity<Integer> statisticsLastMonthCountSales() {
        return ResponseEntity.ok(statisticsService.statisticsLastMonthCountSales());
    }

    @GetMapping("/statistics/sales/last-month-count-ended-sales")
    public ResponseEntity<Integer> statisticsLastMonthCountEndedSales() {
        return ResponseEntity.ok(statisticsService.statisticsLastMonthCountEndedSales());
    }

    @GetMapping("/statistics/sales/ads-type-count-sales")
    public ResponseEntity<List<Object[]>> statisticsAdsTypeCountSales() {
        return ResponseEntity.ok(statisticsService.statisticsAdsTypeCountSales());
    }



}

