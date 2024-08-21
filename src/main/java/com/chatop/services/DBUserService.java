package com.chatop.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.chatop.model.DBUser;
import com.chatop.model.dto.UserDto;
import com.chatop.model.responses.simpleToken;
import com.chatop.repository.DBUserRepository;

@Service
public class DBUserService {
    
    @Autowired
    private DBUserRepository DBUserRepository;

    @Autowired
    PasswordEncoder PasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDto userDto;

    @Autowired
    private JWTService jwtService;

    public DBUserService(JWTService jwtService) {
		this.jwtService = jwtService;
	}

    public Iterable<DBUser> getUsers(){
        return DBUserRepository.findAll();
    }

    public DBUser createUser(DBUser DBUser){
        DBUser userToAdd = new DBUser(  DBUser.getName(),
                                        DBUser.getEmail(),
                                        new Timestamp(Long.parseLong(LocalDate.now().toString())),
                                        new Timestamp(Long.parseLong(LocalDate.now().toString()))
                                        // PasswordEncoder.encode(DBUser.getPassword())
                                        );
        userToAdd.setPassword(PasswordEncoder.encode(DBUser.getPassword()));
        return userToAdd;
    }

    public void addUser(DBUser DBUser){
        DBUserRepository.save(DBUser);
    }

    public void removeUser(DBUser DBuser){
        DBUserRepository.delete(DBuser);
    }

    public ResponseEntity<?> register(DBUser inputUser) {
        if(DBUserRepository.findByEmail(inputUser.getEmail()) != null){
            return ResponseEntity.badRequest().body("Username already exist");
        }
        DBUser userToAdd = createUser(inputUser);
        DBUserRepository.save(userToAdd);
        
        return ResponseEntity.ok(new simpleToken(jwtService.generateToken(userToAdd)));
    }

    public ResponseEntity<?> login(DBUser inputUser) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(inputUser.getEmail(), inputUser.getPassword()));

            DBUser userToAdd = DBUserRepository.findByEmail(inputUser.getEmail());
            userToAdd.setUpdated_at(new Timestamp(Long.parseLong(LocalDate.now().toString())));
            DBUserRepository.save(userToAdd);
            
            return ResponseEntity.ok(new simpleToken(jwtService.generateToken(userToAdd)));
        } catch (Exception e) {
            System.out.printf("Exception: %s\n",e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    public ResponseEntity<UserDto> getUser() throws ParseException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok().body(userDto.DBUserToObjectUser(DBUserRepository.findByEmail(username)));
    }

    public ResponseEntity<?> getUserDtoById(String id) throws NumberFormatException, ParseException {
        return ResponseEntity.ok().body(userDto.DBUserToObjectUser(DBUserRepository.findById(Integer.parseInt(id))));
    }
}
