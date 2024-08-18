package com.chatop.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chatop.model.DBUser;
import com.chatop.repository.DBUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    DBUserRepository DBUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        DBUser user = DBUserRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return new User(user.getName(), 
                        user.getPassword(), 
                        new ArrayList<>()); 
    }
}
