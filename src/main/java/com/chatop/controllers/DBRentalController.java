package com.chatop.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.model.dto.RentalDto;
import com.chatop.services.DBRentalsService;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/rentals")
public class DBRentalController {

    @Autowired
    private DBRentalsService DBRentalsService;

    @GetMapping("")
    public ResponseEntity<?> getRentals() throws IOException {
        return DBRentalsService.getRentals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRentalsById(@PathVariable Integer id) throws ParseException, IOException {
        return DBRentalsService.getRentalsById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> createRental(  @RequestParam("name") String name,
                                            @RequestParam("surface") BigDecimal surface,
                                            @RequestParam("price") BigDecimal price,
                                            @RequestParam("description") String description,
                                            @RequestParam("picture") MultipartFile picture){
        return DBRentalsService.createRental(   name,
                                                surface,
                                                price,
                                                picture,
                                                description);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRental(  @PathVariable String id, 
                                            RentalDto rentalDto) {
        return DBRentalsService.updateRental(id, rentalDto);
    }       
}
