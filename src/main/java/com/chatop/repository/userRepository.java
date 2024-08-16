package com.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.chatop.model.DBUser;

@Repository
public interface userRepository extends CrudRepository<DBUser, Integer>{
    
}
