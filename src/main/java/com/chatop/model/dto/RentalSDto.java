package com.chatop.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.context.annotation.Configuration;
import com.chatop.model.RentalEntity;
import lombok.Data;

@Data
@Configuration
public class RentalSDto {
    int id;
    String name;
    BigDecimal surface;
    BigDecimal price;
    String picture;
    String description;
    int owner_id;
    String created_at;
    String updated_at;

    public RentalSDto(){}

    public RentalSDto(  String name,
                        BigDecimal surface,
                        BigDecimal price,
                        String picture,
                        String description){
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
    }
    
    public RentalSDto(  int id,
                        String name,
                        BigDecimal surface,
                        BigDecimal price,
                        String picture,
                        String description,
                        int owner_id,
                        String created_at,
                        String updated_at){
        this.id = id;
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.owner_id = owner_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public RentalEntity ToRentalEntity(){
        return new RentalEntity(   this.getName(), 
                                this.getSurface(),
                                this.getPrice(),
                                this.getPicture(),
                                this.getDescription(), 
                                this.getOwner_id(),
                                this.getCreated_at(), 
                                this.getUpdated_at());
    }

    public RentalEntity ToRentalEntities(Optional<RentalEntity> rentals){
        return new RentalEntity(   rentals.get().getName(),
                                rentals.get().getSurface(),
                                rentals.get().getPrice(),
                                rentals.get().getPicture(),
                                rentals.get().getDescription(),
                                rentals.get().getOwnerId(),
                                rentals.get().getCreatedAt(),
                                rentals.get().getUpdatedAt());
    }

    public ArrayList<RentalSDto> IterableDBRentalsToArrayListObjectRentals(Iterable<RentalEntity> rentals){
        ArrayList<RentalSDto> res = new ArrayList<>(); 

        for (RentalEntity dbRentals : rentals) {
            res.add(dbRentals.ToRentalSDto());
        }
        return res;
    }
}
