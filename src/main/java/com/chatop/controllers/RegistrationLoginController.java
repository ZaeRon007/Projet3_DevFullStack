package com.chatop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chatop.model.DBUser;
import com.chatop.model.JwtResponse;
import com.chatop.repository.DBUserRepository;
import com.chatop.services.DBUserService;
import com.chatop.services.JWTService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/auth")
public class RegistrationLoginController {

    @Autowired
    private DBUserService DBUserService;

    @Autowired
    private DBUserRepository DBUserRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public RegistrationLoginController(JWTService jwtService) {
		this.jwtService = jwtService;
	}

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@RequestBody DBUser inputUser){
        if(DBUserRepository.findByEmail(inputUser.getEmail()) != null){
            return ResponseEntity.badRequest().body("Username already exist");
        }
        DBUser userToAdd = DBUserService.createUser(inputUser);
        userToAdd.setToken(jwtService.generateToken(userToAdd));
        DBUserRepository.save(userToAdd);
        
        return ResponseEntity.ok(new JwtResponse(userToAdd.getToken()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody DBUser inputUser) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(inputUser.getEmail(), inputUser.getPassword()));

            DBUser userToAdd = DBUserRepository.findByEmail(inputUser.getEmail());
            userToAdd.setToken(jwtService.generateToken(userToAdd));
            //mise à jour de user
            DBUserRepository.save(userToAdd);
            
            return ResponseEntity.ok(new JwtResponse(userToAdd.getToken()));
        } catch (Exception e) {
            System.out.printf("Exception: %s\n",e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // System.out.printf("username: %s\n",username);
        return ResponseEntity.ok().body(DBUserRepository.findByEmail(username));
    }
    
}
