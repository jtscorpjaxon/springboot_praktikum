package uz.praktikum.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.praktikum.springboot.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


}
