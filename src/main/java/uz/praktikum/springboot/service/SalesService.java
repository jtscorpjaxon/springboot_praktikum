package uz.praktikum.springboot.service;

import org.springframework.stereotype.Service;
import uz.praktikum.springboot.entity.Sales;
import uz.praktikum.springboot.repository.SalesRepository;

import java.util.List;

@Service
public class SalesService {
    private final SalesRepository salesRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }


    public Sales save(Sales sales){
            return salesRepository.save(sales);
    }

    public void delete(Long id){
        salesRepository.deleteById(id);
    }

    public List<Sales> getSales() {
        return salesRepository.findAll();
    }

    public Sales getSales(Long id) {
        return salesRepository.findById(id).orElse(null);
    }
}
