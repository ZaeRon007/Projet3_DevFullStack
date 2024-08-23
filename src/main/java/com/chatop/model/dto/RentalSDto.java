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
    
    public RentalSDto(   int id,
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

    public DBRentals ToDBRental(){
        return new DBRentals(   this.getName(), 
                                this.getSurface(),
                                this.getPrice(),
                                this.getPicture(),
                                this.getDescription(), 
                                this.getOwner_id(),
                                this.getCreated_at(), 
                                this.getUpdated_at());
    }

    public DBRentals ToDBRentals(Optional<DBRentals> rentals){
        return new DBRentals(   rentals.get().getName(),
                                rentals.get().getSurface(),
                                rentals.get().getPrice(),
                                rentals.get().getPicture(),
                                rentals.get().getDescription(),
                                rentals.get().getOwner_id(),
                                rentals.get().getCreated_at(),
                                rentals.get().getUpdated_at());
    }

    public ArrayList<RentalSDto> IterableDBRentalsToArrayListObjectRentals(Iterable<DBRentals> rentals){
        ArrayList<RentalSDto> res = new ArrayList<>(); 

        for (DBRentals dbRentals : rentals) {
            res.add(dbRentals.ToRentalDto());
        }
        return res;
    }
}
