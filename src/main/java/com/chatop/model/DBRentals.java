package com.chatop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rentals")
public class DBRentals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    
    @Column(name = "surface")
    private int surface;
    
    @Column(name = "price")
    private int price;

    @Column(name = "picture")
    private String picture;
    
    @Column(name = "description", length = 1000)
    private String description;
    
    @Column(name = "owner_id")
    private int owner_id;
    
    @Column(name = "created_at")
    private String created_at;
    
    @Column(name = "updated_at")
    private String updated_at;
}
