package com.chatop.model;

import java.sql.Timestamp;

import com.chatop.model.dto.MessageDto;

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
    private int rentalId;

    @Column(name = "user_id")
    private int userId;
    
    @Column(name = "message", length = 2000)
    private String message;

    @Column(name = "created_at")
    private String createdAt;
    
    @Column(name = "updated_at")
    private String updatedAt;

    public DBMessages(){}

    public DBMessages(  int rentalId,
                        int userId,
                        String message,
                        String createdAt,
                        String updatedAt){
        this.rentalId = rentalId;
        this.userId = userId;
        this.message = message;          
        this.createdAt = createdAt;
        this.updatedAt = updatedAt; 
    }

    public MessageDto ToMessageDto(){
        return new MessageDto(  this.getRentalId(),
                                this.getUserId(),
                                this.getMessage());
    }

}
