package com.chatop.service;

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
}
