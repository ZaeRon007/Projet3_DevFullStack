package com.chatop.model;

import java.math.BigDecimal;
import com.chatop.model.dto.RentalSDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rentals")
@Schema(description = "Modèle location pour la base de données")
public class DBRentals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surface")
    private BigDecimal surface;
    
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "picture")
    private String picture;
    
    @Column(name = "description", length = 2000)
    private String description;
    
    @Column(name = "owner_id")
    private int ownerId;
    
    @Column(name = "created_at")
    private String createdAt;
    
    @Column(name = "updated_at")
    private String updatedAt;

    public DBRentals(){}

    public DBRentals(   String name,
                        BigDecimal surface,
                        BigDecimal price,
                        String picture,
                        String description,
                        int ownerId,
                        String createdAt,
                        String updatedAt){
        
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public RentalSDto ToRentalSDto(){
        return new RentalSDto(   this.getId(),
                                this.getName(), 
                                this.getSurface(),
                                this.getPrice(),
                                this.getPicture(),
                                this.getDescription(), 
                                this.getOwnerId(),
                                this.getCreatedAt(), 
                                this.getUpdatedAt());
    }
}
