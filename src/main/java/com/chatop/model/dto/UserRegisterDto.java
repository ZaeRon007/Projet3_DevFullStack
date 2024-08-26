package com.chatop.model.dto;

import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
public class UserRegisterDto {
    String name;
    String email;
    String password;

    public UserRegisterDto(){}

    public UserRegisterDto( String name,
                            String email,   
                            String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}