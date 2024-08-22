package com.chatop.model.dto;

import com.chatop.model.DBMessages;
import lombok.Data;

@Data
public class MessageDto {
    
    private int rental_id;
    private int user_id;
    private String message;

    public MessageDto(){}

    public MessageDto(  int rental_id,
                        int user_id,
                        String message){

        this.rental_id = rental_id;
        this.user_id = user_id;
        this.message = message;          
    }

    public MessageDto DBMessagesToMessageDto(DBMessages inDbMessages){
        return new MessageDto(  inDbMessages.getRental_id(),
                                inDbMessages.getUser_id(),
                                inDbMessages.getMessage());
    }
}