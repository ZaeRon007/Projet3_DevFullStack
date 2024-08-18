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


@RestController
@RequestMapping("/api/auth")
public class DBUserController {

    @Autowired
    private DBUserService DBUserService;

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@RequestBody DBUser inputUser){
        return DBUserService.register(inputUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody DBUser inputUser) {
        return DBUserService.login(inputUser);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe() throws ParseException {
        return DBUserService.getUser();
    }
    
}
