package com.chatop.model;


import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class DBUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;
    
    @Column(name = "created_at")
    private String created_at;
    
    @Column(name = "updated_at")
    private String updated_at;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;


    public DBUser(){}

    public DBUser(  int id,
                    String name,
                    String email,
                    String created_at,
                    String updated_at,
                    String password,
                    String token){
        this.id = id;
        this.name = name;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at; 
        this.password = password;
        this.token = token;                
    }
}