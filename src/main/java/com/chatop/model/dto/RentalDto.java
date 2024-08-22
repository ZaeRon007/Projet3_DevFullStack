package com.chatop.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import com.chatop.model.DBRentals;
import lombok.Data;

@Data
@Configuration
public class RentalDto {
    int id;
    String name;
    BigDecimal surface;
    BigDecimal price;
    MultipartFile pictureFile;
    String picture;
    String description;
    int ownerId;
    String createdAt;
    String updatedAt;

    public RentalDto(){}
    
    public RentalDto(   int id,
                        String name,
                        BigDecimal surface,
                        BigDecimal price,
                        String picture,
                        String description,
                        int ownerId,
                        String createdAt,
                        String updatedAt){
        this.id = id;
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public DBRentals ToDBRental(){
        return new DBRentals(   this.getName(), 
                                this.getSurface(),
                                this.getPrice(),
                                this.getPicture(),
                                this.getDescription(), 
                                this.getOwnerId(),
                                this.getCreatedAt(), 
                                this.getUpdatedAt());
    }

    public DBRentals ToDBRentals(Optional<DBRentals> rentals){
        return new DBRentals(   rentals.get().getName(),
                                rentals.get().getSurface(),
                                rentals.get().getPrice(),
                                rentals.get().getPicture(),
                                rentals.get().getDescription(),
                                rentals.get().getOwnerId(),
                                rentals.get().getCreatedAt(),
                                rentals.get().getUpdatedAt());
    }

    public ArrayList<RentalDto> IterableDBRentalsToArrayListObjectRentals(Iterable<DBRentals> rentals){
        ArrayList<RentalDto> res = new ArrayList<>(); 

        for (DBRentals dbRentals : rentals) {
            res.add(dbRentals.ToRentalDto());
        }
        return res;
    }
}
