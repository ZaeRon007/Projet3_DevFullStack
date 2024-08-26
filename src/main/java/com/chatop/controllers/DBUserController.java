package com.chatop.controllers;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chatop.model.dto.UserLoginDto;
import com.chatop.model.dto.UserRegisterDto;
import com.chatop.services.DBUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Tag(name = "User Controller", description = "Gesture component for users")
@RestController
@RequestMapping("/api")
public class DBUserController {

    @Autowired
    private DBUserService DBUserService;

    @Operation(
        summary = "Allow user to register into database",
        description = "Allow user to register specifying name, email and password"
    )
    @PostMapping("/auth/register")
    public ResponseEntity<?> userRegister(@RequestBody UserRegisterDto userRegisterDto){
        return DBUserService.register(userRegisterDto);
    }

    @Operation(
        summary = "Allow user to login",
        description = "Allow user to login specifying email and password"
    )
    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto) {
        return DBUserService.login(userLoginDto);
    }

    @Operation(
        summary = "Allow user see its account informations",
        description = "Display user's informations like name, email, creation date and last update one"
    )
    @GetMapping("/auth/me")
    public ResponseEntity<?> getMe() throws ParseException {
        return DBUserService.getUser();
    }

    @Operation(
        summary = "Allow front to get a user by its id",
        description = "Allow front-end to get a user specifying its id"
    )
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserDtoById(@PathVariable String id) throws NumberFormatException, ParseException {
        return DBUserService.getUserDtoById(id);
    }   
}
