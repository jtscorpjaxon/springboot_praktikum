package uz.praktikum.springboot.service;

import org.springframework.stereotype.Service;
import uz.praktikum.springboot.entity.Role;
import uz.praktikum.springboot.repository.RoleRepository;


@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
