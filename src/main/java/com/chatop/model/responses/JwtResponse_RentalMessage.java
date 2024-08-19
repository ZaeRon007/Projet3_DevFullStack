package com.chatop.model.responses;

import lombok.Data;

@Data
public class JwtResponse_RentalMessage {
    private String message;
    private int user_id;
    private int rental_id;

    public JwtResponse_RentalMessage(   String message,
                                        int user_id,
                                        int rental_id) {
        this.message = message;
        this.user_id = user_id;
        this.rental_id = rental_id;
    }
}