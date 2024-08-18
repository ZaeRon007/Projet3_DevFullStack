package com.chatop.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.chatop.services.DBRentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api")
public class DBRentalController {

    @Autowired
    private DBRentalsService DBRentalsService;

    @GetMapping("/rentals")
    public ResponseEntity<?> getRentals() {
        return DBRentalsService.getRentals();
    }

    @GetMapping("/rentals/{id}")
    public ResponseEntity<?> getRentalsById(@PathVariable Integer id) {
        return DBRentalsService.getRentalsById(id);
    }

    @PostMapping("/rentals")
    public ResponseEntity<?> createRental(  @RequestParam("name") String name,
                                            @RequestParam("surface") int surface,
                                            @RequestParam("price") int price,
                                            @RequestParam("description") String description,
                                            @RequestParam("picture") MultipartFile picture){
        return DBRentalsService.createRental(   name,
                                                surface,
                                                price,
                                                description,
                                                picture);
    }

     
    
    @PutMapping("/rentals/{id}")
    public ResponseEntity<?> updateRental(  @PathVariable String id, 
                                            @RequestParam("name") String name,
                                            @RequestParam("surface") int surface,
                                            @RequestParam("price") int price,
                                            @RequestParam("description") String description) {
        return DBRentalsService.updateRental(id, name, surface, price, description);
    }   
}
