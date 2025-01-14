package uz.praktikum.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.praktikum.springboot.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByApiName(String name);
    Boolean existsByApiName(String name);
}
