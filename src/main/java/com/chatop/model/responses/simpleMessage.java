package com.chatop.model.responses;

import lombok.Data;

@Data
public class simpleMessage {
    private String message;

    public simpleMessage(String message) {
        this.message = message;
    }
}
