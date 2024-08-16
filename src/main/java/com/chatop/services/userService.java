package com.chatop.services;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.chatop.model.DBUser;
import com.chatop.repository.userRepository;

@Service
public class userService {
    
    @Autowired
    private userRepository userRepository;

    @Autowired
	private JWTService jwtService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public userService(JWTService jwtService) {
		this.jwtService = jwtService;
	}

    public Iterable<DBUser> getUsers(){
        return userRepository.findAll();
    }

    public Optional<DBUser> getUserById(Integer Id){
        return userRepository.findById(Id);
    }

    public DBUser createUser(DBUser user){
        DBUser userToAdd = new DBUser();
        userToAdd.setName(user.getName());
        userToAdd.setEmail(user.getEmail());
        userToAdd.setCreated_at(LocalDate.now().toString());
        userToAdd.setUpdated_at(LocalDate.now().toString());
        userToAdd.setToken(jwtService.generateToken(user));
        userToAdd.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userToAdd;
    }

    public void addUser(DBUser user){

        if(!doesEmailExist(user))
            userRepository.save(user);
        else
            System.out.println("user already exist!!\n");        
    }

    public void removeUser(DBUser user){
        userRepository.delete(user);
    }
    
    // private boolean doesUserExist(user user){
    //     return userRepository.existsById(user.getId());
    // }

    private boolean doesEmailExist(DBUser user){
        Iterable<DBUser> users = getUsers();

        for (DBUser uElement : users) {
            if(uElement.getEmail().equals(user.getEmail())){
                return true;
            }
        }
        return false;
    }

    public DBUser getUserByEmail(String email){
        Iterable<DBUser> users = getUsers();

        for (DBUser uElement : users) {
            if(uElement.getEmail().equals(email)){
                return uElement;
            }
        }
        System.err.println("user doesn't exist!!\n");
        return new DBUser();
    }
    
}
