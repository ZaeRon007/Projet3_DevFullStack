package com.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.chatop.model.UserEntity;



@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{
    UserEntity findByEmail(String email);
    UserEntity findByName(String name);
    UserEntity findById(int id);
    boolean existsByEmail(String email);
}
