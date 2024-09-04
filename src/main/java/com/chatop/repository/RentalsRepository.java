package com.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.chatop.model.RentalEntity;


@Repository
public interface RentalsRepository extends CrudRepository<RentalEntity, Integer>{
    RentalEntity findById(int id);

}
