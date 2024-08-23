package com.chatop.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.chatop.model.dto.RentalMDto;
import com.chatop.model.dto.RentalSDto;
import com.chatop.services.DBRentalsService;
import java.io.IOException;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<?> createRental(RentalMDto rentalMDto){
        return DBRentalsService.createRental(rentalMDto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRental(  @PathVariable String id, 
                                            RentalSDto rentalDto) {
        return DBRentalsService.updateRental(id, rentalDto);
    }       
}
