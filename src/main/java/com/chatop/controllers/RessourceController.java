package com.chatop.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.chatop.model.DBRentals;
import com.chatop.model.DBUser;
import com.chatop.services.rentalsService;
import com.chatop.services.userService;

import jakarta.persistence.Column;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api")
public class RessourceController {

    @Autowired
    private rentalsService rentalsService;

    // @Autowired
    // private userService userService;

    @GetMapping("/rentals")
    public Iterable<DBRentals> getRentals() {
        return rentalsService.getRentals();
    }

    @GetMapping("/rentals/:id")
    public Optional<DBRentals> getRentalsById(Integer id) {
        return rentalsService.getRentalById(id);
    }

    @PostMapping("/rentals/:id")
    public void createRental(@RequestBody DBRentals rental) {
        rentalsService.addRentals(rental);
    }

    @PutMapping("/rentals/{id}")
    public void updateRental(@PathVariable String id, @RequestBody DBRentals rental) {
        rentalsService.updateRental(id, rental);
    }   
}
