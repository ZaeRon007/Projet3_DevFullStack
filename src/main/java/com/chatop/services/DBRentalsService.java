package com.chatop.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.model.DBRentals;
import com.chatop.repository.DBRentalsRepository;

@Service
public class DBRentalsService {
    
    @Autowired
    private DBRentalsRepository DBRentalsRepository;

    public Iterable<DBRentals> getRentals(){
        return DBRentalsRepository.findAll();
    }

    public Optional<DBRentals> getRentalById(Integer Id){
        return DBRentalsRepository.findById(Id);
    }

    public void addRentals(DBRentals DBRentals){
        DBRentalsRepository.save(DBRentals);
    }

    public void removeRental(DBRentals DBRentals){
        DBRentalsRepository.delete(DBRentals);
    }

    public void updateRental(String id, DBRentals DBRentals){
        DBRentals.setId(Integer.parseInt(id));
        DBRentalsRepository.save(DBRentals);
    }


}
