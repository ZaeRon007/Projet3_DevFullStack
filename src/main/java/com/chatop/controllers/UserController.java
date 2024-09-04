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
import com.chatop.model.responses.simpleToken;
import com.chatop.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Tag(name = "User Controller", description = "Gesture component for users")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService DBUserService;

    @Operation(
        summary = "Allow user to register into database",
        description = "Allow user to register specifying name, email and password"
    )
    @PostMapping("/auth/register")
    public ResponseEntity<?> userRegister(@RequestBody UserRegisterDto userRegisterDto){
        String token = DBUserService.register(userRegisterDto);

        if(token.isEmpty())
            return ResponseEntity.badRequest().body("Username already exist !");

    return ResponseEntity.ok().body(new simpleToken(token));

    }

    @Operation(
        summary = "Allow user to login",
        description = "Allow user to login specifying email and password"
    )
    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto) {
        String token = DBUserService.login(userLoginDto);

        if(token.isEmpty())
            return ResponseEntity.badRequest().body("Username or Password in invalid !");

        return ResponseEntity.ok().body(new simpleToken(token));

    }

    @Operation(
        summary = "Allow user see its account informations",
        description = "Display user's informations like name, email, creation date and last update one"
    )
    @GetMapping("/auth/me")
    public ResponseEntity<?> getMe() throws ParseException {
        return ResponseEntity.ok().body(DBUserService.getUser());
    }

    @Operation(
        summary = "Allow front to get a user by its id",
        description = "Allow front-end to get a user specifying its id"
    )
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserDtoById(@PathVariable String id) throws NumberFormatException, ParseException {
        return ResponseEntity.ok().body(DBUserService.getUserDtoById(id));
    }   
}
