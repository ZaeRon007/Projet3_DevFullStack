package com.chatop.model.responses;

import lombok.Data;

@Data
public class JwtResponse_Token {
    private String token;

    public JwtResponse_Token(String token) {
        this.token = token;
    }
}