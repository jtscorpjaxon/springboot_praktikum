package uz.praktikum.springboot.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import uz.praktikum.springboot.entity.Client;
import uz.praktikum.springboot.entity.Employee;
import uz.praktikum.springboot.entity.enumration.EmployeePosition;
import uz.praktikum.springboot.repository.ClientRepository;


import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final EmployeeService employeeService;

    public ClientService(ClientRepository clientRepository, EmployeeService employeeService) {
        this.clientRepository = clientRepository;
        this.employeeService = employeeService;
    }

    public Client save(Authentication authentication,Client client) {
        if (
                !clientRepository.existsByPassportJSHSHIR(client.getPassport().getJSHSHIR())
        ) {
            client.setEmployee(employeeService.getEmployee(authentication));
            return clientRepository.save(client);
        }
        return clientRepository.findByPassportJSHSHIR(client.getPassport().getJSHSHIR());
    }

    public List<Client> findAll(Authentication authentication) {

        if (getAccess(authentication)) {
            return clientRepository.findAllByArchive(false);
        }
        return null;
    }

    public Boolean getAccess(Authentication authentication) {
        Employee employee = employeeService.getEmployee(authentication);
        return employee.getDepartment().getName().equalsIgnoreCase("Client") ||
                employee.getEmployeePosition().equals(EmployeePosition.DEPARTMENT_HEAD) ||
                employee.getEmployeePosition().equals(EmployeePosition.DIRECTOR);
    }

    public Client findById(Authentication authentication, Long id) {
        Optional<Client> optional = clientRepository.findById(id);
        if (optional.isPresent() && getAccess(authentication)) {
            return optional.get();
        }
        return null;
    }

    public Client update(Authentication authentication, Client client) {
        if (getAccess(authentication)) {
            return clientRepository.save(client);
        }
        return null;
    }

    public void delete(Authentication authentication, Long id) {
        if (getAccess(authentication)) {
            if (employeeService.getEmployee(authentication).getEmployeePosition() == EmployeePosition.DIRECTOR) {
                clientRepository.deleteById(id);
            } else {
                Client client = clientRepository.findById(id).orElseThrow();
                client.setArchive(true);
                clientRepository.save(client);
            }
        }
    }
}
