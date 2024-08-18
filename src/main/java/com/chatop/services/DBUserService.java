package com.chatop.services;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.chatop.model.DBUser;
import com.chatop.repository.DBUserRepository;

@Service
public class DBUserService {
    
    @Autowired
    private DBUserRepository DBUserRepository;

    @Autowired
    PasswordEncoder PasswordEncoder;

    public Iterable<DBUser> getUsers(){
        return DBUserRepository.findAll();
    }

    public DBUser createUser(DBUser DBUser){
        DBUser userToAdd = new DBUser();
        userToAdd.setName(DBUser.getName());
        userToAdd.setEmail(DBUser.getEmail());
        userToAdd.setCreated_at(LocalDate.now().toString());
        userToAdd.setUpdated_at(LocalDate.now().toString());
        userToAdd.setPassword(PasswordEncoder.encode(DBUser.getPassword()));
        return userToAdd;
    }

    public void addUser(DBUser DBUser){
        DBUserRepository.save(DBUser);
    }

    public void removeUser(DBUser DBuser){
        DBUserRepository.delete(DBuser);
    }
}
