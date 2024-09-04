package com.chatop.model.dto;

import org.springframework.context.annotation.Configuration;
import com.chatop.model.UserEntity;
import lombok.Data;

@Data
@Configuration
public class UserDto {
    int id;
    String name;
    String email;
    String created_at;
    String updated_at;

    public UserDto(){}

    public UserDto( int id,
                    String name,
                    String email,
                    String created_at,
                    String updated_at){
        this.id = id;
        this.name = name;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public UserEntity ToUserEntity(){
        return new UserEntity(  this.getName(),
                            this.getEmail(),
                            this.getCreated_at(),
                            this.getUpdated_at());
    }
}