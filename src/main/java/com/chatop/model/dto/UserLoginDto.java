package com.chatop.model.dto;

import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
public class UserLoginDto {
    String email;
    String password;

    public UserLoginDto(){}

    public UserLoginDto(    String email,
                            String password){
        this.email = email;
        this.password = password;
    }
}