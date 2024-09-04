package com.chatop.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.chatop.model.dto.RentalMDto;
import com.chatop.model.dto.RentalUpdateDto;
import com.chatop.model.responses.ArrayListOfDtoRentals;
import com.chatop.model.responses.simpleMessage;
import com.chatop.services.RentalsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Rental Controller", description = "Gesture component for rentals")
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalsService rentalsService;

    @Operation(
        summary = "Allow user to get all rentals",
        description = "Allow user to pull all rentals from database"
    )
    @GetMapping("")
    public ResponseEntity<?> getRentals() throws IOException {
        return ResponseEntity.ok().body( new ArrayListOfDtoRentals(rentalsService.getRentals()));
    }

    @Operation(
        summary = "Allow user to get a particular rental",
        description = "Allow user to pull a rental from database specifying its id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getRentalsById(@PathVariable Integer id) throws ParseException, IOException {
        return ResponseEntity.ok().body(rentalsService.getRentalsById(id));
    }

    @Operation(
        summary = "Allow user to create a rental",
        description = "Allow user to create a rental specifying its name, surface, price, pictureFile and its description"
    )
    @PostMapping(path = "", consumes = { "multipart/form-data" })
    public ResponseEntity<?> createRental( RentalMDto rentalMDto){
        return ResponseEntity.ok().body(rentalsService.createRental(rentalMDto));
    }
    
    @Operation(
        summary = "Allow user to update a rental",
        description = "Allow user to update a rental specifying its name, surface, price and its description"
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRental(  @PathVariable String id, 
                                            RentalUpdateDto rentalUpdateDto) {
        rentalsService.updateRental(id, rentalUpdateDto);
        return ResponseEntity.ok().body(new simpleMessage("Rental updated !"));

    }       
}
