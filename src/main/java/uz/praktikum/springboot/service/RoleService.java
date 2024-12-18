package uz.praktikum.springboot.service;

import org.springframework.stereotype.Service;
import uz.praktikum.springboot.entity.Role;
import uz.praktikum.springboot.repository.RoleRepository;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role save(Role role){
        if(!roleRepository.existsByName(role.getName())){
            return roleRepository.save(role);
        }
        return roleRepository.findByName(role.getName());
    }

    public void delete(Long id){
        roleRepository.deleteById(id);
    }
}
