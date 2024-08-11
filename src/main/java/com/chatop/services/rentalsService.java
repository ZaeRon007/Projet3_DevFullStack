package com.chatop.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.model.rentals;
import com.chatop.repository.rentalsRepository;

@Service
public class rentalsService {
    
    @Autowired
    private rentalsRepository rentalsRepository;

    public Iterable<rentals> getRentals(){
        return rentalsRepository.findAll();
    }

    public Optional<rentals> getRentalById(Integer Id){
        return rentalsRepository.findById(Id);
    }

    public rentals addRentals(rentals rental){
        return rentalsRepository.save(rental);
    }

    public void removeRental(rentals rental){
        rentalsRepository.delete(rental);
    }
}
