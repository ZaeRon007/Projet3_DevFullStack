package com.chatop.model.dto;

import java.sql.Timestamp;

import org.springframework.context.annotation.Configuration;

import com.chatop.model.DBUser;
import lombok.Data;

@Data
@Configuration
public class UserDto {
    int id;
    String name;
    String email;
    String createdAt;
    String updatedAt;

    public UserDto(){}

    public UserDto( int id,
                    String name,
                    String email,
                    String createdAt,
                    String updatedAt){
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public DBUser ToDBUser(){
        return new DBUser(  this.getName(),
                            this.getEmail(),
                            this.getCreatedAt(),
                            this.getUpdatedAt());
    }
}