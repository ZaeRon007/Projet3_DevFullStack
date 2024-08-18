package com.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.chatop.model.DBRentals;


@Repository
public interface DBRentalsRepository extends CrudRepository<DBRentals, Integer>{
    DBRentals findById(int id);

}
