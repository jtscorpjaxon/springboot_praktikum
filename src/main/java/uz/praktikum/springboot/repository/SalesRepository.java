package uz.praktikum.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.praktikum.springboot.entity.Sales;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

    //Sum max amount  sales find ads type
    @Query("SELECT SUM(s.amount),s.adsType FROM Sales s group by s.adsType order by SUM(s.amount) desc limit 1")
    Object[] sumMaxAmountSalesFindAdsType();

    //find employee most amount sales
    @Query("SELECT s.employee,sum(s.amount) FROM Sales s group by s.employee order by sum(s.amount) desc limit 1")
    Object[] findEmployeeMostAmountSales();

    //statistics last month count sales
    @Query("SELECT count(s) FROM Sales s where s.createdAt > current_date - interval ('1') month")
    Integer statisticsLastMonthCountSales();

    //statistics last month count ended sales
    @Query("SELECT count(s) FROM Sales s where  s.duration between current_date - interval ('1') month and current_date")
    Integer statisticsLastMonthCountEndedSales();

    //statistics ads type count sales
    @Query("SELECT count(s),s.adsType FROM Sales s group by s.adsType")
    List<Object[]> statisticsAdsTypeCountSales();
}
