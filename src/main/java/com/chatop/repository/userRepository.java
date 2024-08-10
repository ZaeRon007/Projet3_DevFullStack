package com.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.chatop.model.user;

@Repository
public interface userRepository extends CrudRepository<user, Integer>{
    
}
