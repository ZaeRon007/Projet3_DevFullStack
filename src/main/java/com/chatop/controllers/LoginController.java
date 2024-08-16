package com.chatop.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chatop.model.DBUser;
import com.chatop.services.JWTService;
import com.chatop.services.userService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private userService userService;

    @Autowired
	private JWTService jwtService;


	public LoginController(JWTService jwtService) {
		this.jwtService = jwtService;
	}

    @PostMapping("/register")
    public String userRegister(@RequestBody DBUser user){
        DBUser userToAdd = userService.createUser(user);
        userService.addUser(userToAdd);//Créer l'utilisateur

        return userToAdd.getToken();
    }

    @PostMapping("/login")
    public String getToken2(@RequestBody UserDetails entity) {
        
        
        return "";
    }
    
	public String getToken(DBUser user) {
		String token = jwtService.generateToken(user);
		return token;
	}

}
