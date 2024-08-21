package com.chatop.model;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
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
    private DecimalFormat surface;
    
    @Column(name = "price")
    private DecimalFormat price;

    @Column(name = "picture")
    private String picture;
    
    @Column(name = "description", length = 2000)
    private String description;
    
    @Column(name = "owner_id")
    private int owner_id;
    
    @Column(name = "created_at")
    private Timestamp created_at;
    
    @Column(name = "updated_at")
    private Timestamp updated_at;

    public DBRentals(){}

    public DBRentals(   String name,
                        DecimalFormat surface,
                        DecimalFormat price,
                        String picture,
                        String description,
                        // int owner_id,
                        Timestamp created_at,
                        Timestamp updated_at){
        
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
        // this.owner_id = owner_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
