package com.chatop.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatop.model.user;
import com.chatop.repository.userRepository;

@Service
public class userService {
    
    @Autowired
    private userRepository userRepository;

    public Iterable<user> getUsers(){
        return userRepository.findAll();
    }

    public Optional<user> getUserById(Integer Id){
        return userRepository.findById(Id);
    }

    public user addUser(user user){
        return userRepository.save(user);
    }

    public void removeUser(user user){
        userRepository.delete(user);
    }
}
