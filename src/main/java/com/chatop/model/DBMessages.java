package com.chatop.model;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class DBMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "rental_id")
    private int rental_id;

    @Column(name = "user_id")
    private int user_id;
    
    @Column(name = "message", length = 2000)
    private String message;

    @Column(name = "created_at")
    private Timestamp created_at;
    
    @Column(name = "updated_at")
    private Timestamp updated_at;

    public DBMessages(){}

    public DBMessages(  int id,
                        int rental_id,
                        int user_id,
                        String message,
                        Timestamp created_at,
                        Timestamp updated_at){
        this.id = id;
        this.rental_id = rental_id;
        this.user_id = user_id;
        this.message = message;          
        this.created_at = created_at;
        this.updated_at = updated_at; 
    }
}
