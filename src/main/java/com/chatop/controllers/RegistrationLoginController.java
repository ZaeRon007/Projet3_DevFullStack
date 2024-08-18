package com.chatop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chatop.model.DBUser;
import com.chatop.model.LoginModel;
import com.chatop.repository.DBUserRepository;
import com.chatop.services.JWTService;
import com.chatop.services.DBUserService;

@RestController
@RequestMapping("/api/auth")
public class RegistrationLoginController {

    @Autowired
    private DBUserService DBUserService;

    @Autowired
    private DBUserRepository DBUserRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@RequestBody DBUser inputUser){
        if(DBUserRepository.findByUsername(inputUser.getUsername()) != null){
            return ResponseEntity.badRequest().body("Username already exist");
        }
        DBUser userToAdd = DBUserService.createUser(inputUser);
        return ResponseEntity.ok(DBUserRepository.save(userToAdd));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody DBUser inputUser) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(inputUser.getUsername(), inputUser.getPassword()));
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
