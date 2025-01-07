package uz.praktikum.springboot.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.praktikum.springboot.entity.Client;
import uz.praktikum.springboot.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientResource {

    private final ClientService clientsService;


    public ClientResource(ClientService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping("/clients")
    public ResponseEntity getClients(Authentication authentication) {
        List<Client> result = clientsService.findAll(authentication);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity getClient(Authentication authentication,@PathVariable Long id) {
        Client result = clientsService.findById(authentication,id);
        return ResponseEntity.ok(result);
    }

    @PostMapping( "/clients")
    public ResponseEntity createClient(Client clients) {
        Client result = clientsService.save(clients);
        return ResponseEntity.ok(result);
    }

    @PutMapping( "/clients")
    public ResponseEntity updateClient(@RequestBody Client clients) {
        if(clients.getId()==null){
            return ResponseEntity.badRequest().build();
        }
        Client result = clientsService.save(clients);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity deleteClient(Authentication authentication,@PathVariable Long id) {
        clientsService.delete(authentication,id);
        return ResponseEntity.ok().build();
    }
}

