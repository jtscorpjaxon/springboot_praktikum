package uz.praktikum.springboot.service;

import org.springframework.stereotype.Service;
import uz.praktikum.springboot.entity.Department;
import uz.praktikum.springboot.entity.Role;
import uz.praktikum.springboot.repository.DepartmentRepository;
import uz.praktikum.springboot.repository.RoleRepository;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public Department save(Department department){
        if(!departmentRepository.existsByName(department.getName())){
            return departmentRepository.save(department);
        }
        return null;
    }

    public void delete(Long id){
        departmentRepository.deleteById(id);
    }
}
