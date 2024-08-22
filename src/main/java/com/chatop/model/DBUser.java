package com.chatop.model;

import jakarta.persistence.Id;
import java.sql.Timestamp;
import com.chatop.model.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class DBUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;
    
    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private String createdAt;
    
    @Column(name = "updated_at")
    private String updatedAt;

    public DBUser(){}

    public DBUser(  String name,
                    String email,
                    String createdAt,
                    String updatedAt
                    ){
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt; 
    }

    public UserDto ToUserDto() {
        return new UserDto( this.getId(),
                            this.getName(), 
                            this.getEmail(), 
                            this.getCreatedAt(), 
                            this.getUpdatedAt());
    }
}