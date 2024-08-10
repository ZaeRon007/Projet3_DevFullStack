package com.chatop.service;

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
}
