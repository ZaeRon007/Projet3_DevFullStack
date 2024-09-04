package com.chatop.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.chatop.model.UserEntity;
import com.chatop.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    UserRepository DBUserRepository;

    /**
     * load a user from database by it's email
     * @param email
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        UserEntity user = DBUserRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return new User(user.getName(), 
                        user.getPassword(), 
                        new ArrayList<>()); 
    }
}
