package com.chatop.model;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;

    // Constructeur
    public JwtResponse(String token) {
        this.token = token;
    }
}