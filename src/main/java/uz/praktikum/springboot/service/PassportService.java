package uz.praktikum.springboot.service;

import org.springframework.stereotype.Service;
import uz.praktikum.springboot.entity.Passport;
import uz.praktikum.springboot.repository.PassportRepository;

import java.util.Optional;

@Service
public class PassportService {
    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }


    public Passport save(Passport passport){
        if(!passportRepository.existsByJSHSHIR(passport.getJSHSHIR())){
            return passportRepository.save(passport);
        }
        return passportRepository.findByJSHSHIR(passport.getJSHSHIR());
    }

    public void delete(Long id){
        passportRepository.deleteById(id);
    }
}
