package com.chatop.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.chatop.model.DBRentals;
import com.chatop.services.DBRentalsService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api")
public class RessourceController {

    @Autowired
    private DBRentalsService DBRentalsService;

    // @Autowired
    // private userService userService;

    @GetMapping("/rentals")
    public Iterable<DBRentals> getRentals() {
        return DBRentalsService.getRentals();
    }

    @GetMapping("/rentals/:id")
    public Optional<DBRentals> getRentalsById(Integer id) {
        return DBRentalsService.getRentalById(id);
    }

    @PostMapping("/rentals/:id")
    public void createRental(@RequestBody DBRentals DBRentals) {
        DBRentalsService.addRentals(DBRentals);
    }

    @PutMapping("/rentals/{id}")
    public void updateRental(@PathVariable String id, @RequestBody DBRentals DBRentals) {
        DBRentalsService.updateRental(id, DBRentals);
    }   
}
