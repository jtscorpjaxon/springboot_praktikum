package uz.praktikum.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.praktikum.springboot.entity.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {


}