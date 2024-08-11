package com.chatop.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chatop.model.user;
import com.chatop.services.JWTService;
import com.chatop.services.userService;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private userService userService;

	private JWTService jwtService;

	public LoginController(JWTService jwtService) {
		this.jwtService = jwtService;
	}

	@PostMapping("/register")
    public String userRegister(Authentication authentication){
        // récuperer les infos les stocker les infos dans la base sql
        String token = getToken(authentication);
        user user = new user();

        user.setName(authentication.getName());
        user.setEmail(((UserDetails) authentication.getPrincipal()).getUsername());
        user.setCreated_at(LocalDate.now().toString());
        user.setUpdated_at(LocalDate.now().toString());
        user.setToken(token);

        System.out.printf("User: %s %s %s %s token; %s\n",user.getName(),user.getEmail(),user.getCreated_at(),user.getUpdated_at(), user.getToken());
        // userService.addUser(user);//Créer l'utilisateur

        
        // connecter l'utilisateur
        return token;
    }

	public String getToken(Authentication authentication) {
		String token = jwtService.generateToken(authentication);
		return token;
	}

}
