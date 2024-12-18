package uz.praktikum.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.praktikum.springboot.entity.Passport;

import java.util.List;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {

    Boolean existsByJSHSHIR(String jshshir);

    Passport findByJSHSHIR(String jshshir);
}
