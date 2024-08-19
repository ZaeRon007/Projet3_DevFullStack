package com.chatop.controllers;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chatop.model.DBUser;
import com.chatop.services.DBUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api")
public class DBUserController {

    @Autowired
    private DBUserService DBUserService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> userRegister(@RequestBody DBUser inputUser){
        return DBUserService.register(inputUser);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody DBUser inputUser) {
        return DBUserService.login(inputUser);
    }

    @GetMapping("/auth/me")
    public ResponseEntity<?> getMe() throws ParseException {
        return DBUserService.getUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserDtoById(@PathVariable String id) throws NumberFormatException, ParseException {
        return DBUserService.getUserDtoById(id);
    }
    
    
}
