package uz.praktikum.springboot.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.praktikum.springboot.entity.Sales;
import uz.praktikum.springboot.service.SalesService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SalesResource {

    private final SalesService salesService;


    public SalesResource(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/sales")
    public ResponseEntity getSales() {
        List<Sales> result = salesService.getSales();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity getSales(@PathVariable Long id) {
        Sales result = salesService.getSales(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping( "/sales")
    public ResponseEntity createSales(Sales sales) {
        Sales result = salesService.save(sales);
        return ResponseEntity.ok(result);
    }

    @PutMapping( "/sales")
    public ResponseEntity updateSales(@RequestBody Sales sales) {
        if(sales.getId()==null){
            return ResponseEntity.badRequest().build();
        }
        Sales result = salesService.save(sales);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/sales/{id}")
    public ResponseEntity deleteSales(@PathVariable Long id) {
        salesService.delete(id);
        return ResponseEntity.ok().build();
    }
}

