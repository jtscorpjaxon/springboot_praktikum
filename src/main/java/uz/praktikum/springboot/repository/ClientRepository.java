package uz.praktikum.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.praktikum.springboot.entity.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Boolean existsByPassportJSHSHIR(String passportJSHSHIR);
    Client findByPassportJSHSHIR(String passportJSHSHIR);
    List<Client> findAllByArchive(Boolean archive);
}
