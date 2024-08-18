package com.chatop.model;

import lombok.Data;

@Data
public class JwtMessage {
    private String message;

    public JwtMessage(String message) {
        this.message = message;
    }
}
