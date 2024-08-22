package com.chatop.model.dto;

import org.springframework.context.annotation.Configuration;
import java.sql.Timestamp;
import java.text.ParseException;
import com.chatop.model.DBUser;
import lombok.Data;

@Data
@Configuration
public class UserDto {
    String name;
    String email;
    Timestamp created_at;
    Timestamp updated_at;
    // String password;

    public UserDto(){}

    public UserDto( String name,
                    String email,
                    Timestamp created_at,
                    Timestamp updated_at
                    // String password
                    ){
        this.name = name;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
        // this.password = password;
    }
    
    public UserDto DBUserToObjectUser( DBUser  dbuser) throws ParseException{
        return new UserDto( dbuser.getName(), 
                            dbuser.getEmail(), 
                            dbuser.getCreated_at(), 
                            dbuser.getUpdated_at()
                            // dbuser.getPassword()
                            );
    }

    public DBUser ObjectUserToDBUser( UserDto userDto){
        return new DBUser(  userDto.getName(),
                            userDto.getEmail(),
                            userDto.getCreated_at(),
                            userDto.getUpdated_at());
                            // userDto.getPassword());
    }
}