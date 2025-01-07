package uz.praktikum.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.praktikum.springboot.entity.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Boolean existsByPassportJSHSHIR(String passportJSHSHIR);

    Client findByPassportJSHSHIR(String passportJSHSHIR);

    List<Client> findAllByArchive(Boolean archive);

    //Client statistics with daily registered count
    @Query(value = "SELECT COUNT(id), DATE(created_at) FROM client GROUP BY DATE(created_at)", nativeQuery = true)
    List<Object[]> groupByCountWithDailyRegistered();

    //Client statistics with max employee registered count
    @Query(value = "SELECT COUNT(id), employee_id FROM client GROUP BY employee_id ORDER BY COUNT(id) DESC LIMIT 1", nativeQuery = true)
    Object[] groupByCountWithMaxEmployeeRegistered();

    //Client statistics with top 3 employee registered max count
    @Query(value = "SELECT COUNT(id), employee_id FROM client GROUP BY employee_id ORDER BY COUNT(id) DESC LIMIT 3", nativeQuery = true)
    List<Object[]> groupByCountWithTop3EmployeeRegistered();

    //Client statistics with last month registered count
    @Query(value = "SELECT COUNT(id) FROM client WHERE MONTH(created_at) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH)", nativeQuery = true)
    Integer countByLastMonthRegistered();

    //Client statistics with last month registered max day count
    @Query(value = "SELECT COUNT(id), DATE(created_at) FROM client WHERE MONTH(created_at) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH) GROUP BY DATE(created_at) ORDER BY COUNT(id) DESC LIMIT 1", nativeQuery = true)
    Object[] groupByCountWithLastMonthRegisteredMaxDay();

}
