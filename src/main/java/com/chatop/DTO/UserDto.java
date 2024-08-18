package com.chatop.DTO;

import org.springframework.context.annotation.Configuration;
import java.text.ParseException;
import com.chatop.model.DBUser;
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

    public UserDto( String name,
                    String email,
                    String created_at,
                    String updated_at){
        this.name = name;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    
    public UserDto DBUserToObjectUser( DBUser  dbuser) throws ParseException{
        return new UserDto( dbuser.getName(), 
                            dbuser.getEmail(), 
                            dbuser.getCreated_at(), 
                            dbuser.getUpdated_at());
    }

    public DBUser ObjectUserToDBUser( UserDto userDto){
        return new DBUser(  userDto.getName(),
                            userDto.getEmail(),
                            userDto.getCreated_at().toString(),
                            userDto.getUpdated_at().toString(),
                            null,
                            null);
    }
}