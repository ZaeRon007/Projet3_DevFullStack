package com.chatop.model.dto;

import org.springframework.context.annotation.Configuration;
import com.chatop.model.DBMessages;
import com.chatop.services.TimeService;
import lombok.Data;

@Data
@Configuration
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

    public DBMessages ToDBMessages(){
        return new DBMessages(  this.getRental_id(),
                                this.getUser_id(),
                                this.getMessage(),
                                new TimeService().getTime(),
                                new TimeService().getTime());
    }
}