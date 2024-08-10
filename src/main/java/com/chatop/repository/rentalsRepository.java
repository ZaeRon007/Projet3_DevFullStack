package com.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.chatop.model.rentals;

@Repository
public interface rentalsRepository extends CrudRepository<rentals, Integer>{
    
}
