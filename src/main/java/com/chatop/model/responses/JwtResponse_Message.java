package com.chatop.model.responses;

import lombok.Data;

@Data
public class JwtResponse_Message {
    private String message;

    public JwtResponse_Message(String message) {
        this.message = message;
    }
}
