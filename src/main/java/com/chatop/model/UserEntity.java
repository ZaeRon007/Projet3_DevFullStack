package com.chatop.model;

import jakarta.persistence.Id;
import com.chatop.model.dto.UserDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
@Schema(description = "Modèle utilisateur pour la base de données")
public class UserEntity {

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

    public UserEntity(){}

    public UserEntity(  String name,
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