package com.chatop.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.model.DBRentals;
import com.chatop.repository.rentalsRepository;

@Service
public class rentalsService {
    
    @Autowired
    private rentalsRepository rentalsRepository;

    public Iterable<DBRentals> getRentals(){
        return rentalsRepository.findAll();
    }

    public Optional<DBRentals> getRentalById(Integer Id){
        return rentalsRepository.findById(Id);
    }

    public void addRentals(DBRentals rental){
        rentalsRepository.save(rental);
    }

    public void removeRental(DBRentals rental){
        rentalsRepository.delete(rental);
    }

    public void updateRental(String id, DBRentals rental){
        Optional<DBRentals> pRental = getRentalById(Integer.parseInt(id));

        // rentalsRepository.
        String name;
        int surface;
        int price;
        String picture;
        String description;
        int owner_id;
        String updated_at;
    
    }


}
