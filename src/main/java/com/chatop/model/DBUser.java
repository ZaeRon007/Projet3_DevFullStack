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
    private String username;
    
    @Column(name = "created_at")
    private String created_at;
    
    @Column(name = "updated_at")
    private String updated_at;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;

}