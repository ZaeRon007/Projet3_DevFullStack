package com.chatop.model.dto;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.context.annotation.Configuration;
import com.chatop.model.DBRentals;
import lombok.Data;

@Data
@Configuration
public class RentalDto {
    String name;
    DecimalFormat surface;
    DecimalFormat price;
    String picture;
    String description;
    // int owner_id;
    Timestamp created_at;
    Timestamp updated_at;

    public RentalDto(){}
    
    public RentalDto(   String name,
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


    public RentalDto DBRentalToObjectRental( DBRentals  dbRental) throws ParseException, IOException{
        return new RentalDto(  dbRental.getName(), 
                                dbRental.getSurface(),
                                dbRental.getPrice(),
                                dbRental.getPicture(),
                                dbRental.getDescription(), 
                                // dbRental.getOwner_id(),
                                dbRental.getCreated_at(), 
                                dbRental.getUpdated_at());
    }

    public DBRentals ObjectRentalToDBRental( RentalDto RentalDto){
        return new DBRentals(   RentalDto.getName(), 
                                RentalDto.getSurface(),
                                RentalDto.getPrice(),
                                RentalDto.getPicture().toString(),
                                RentalDto.getDescription(), 
                                // RentalDto.getOwner_id(),
                                RentalDto.getCreated_at(), 
                                RentalDto.getUpdated_at());
    }

    public DBRentals OptionnalDBRentalsToDBRentals(Optional<DBRentals> rentals){
        return new DBRentals(   rentals.get().getName(),
                                rentals.get().getSurface(),
                                rentals.get().getPrice(),
                                rentals.get().getPicture(),
                                rentals.get().getDescription(),
                                // rentals.get().getOwner_id(),
                                rentals.get().getCreated_at(),
                                rentals.get().getUpdated_at());
    }

    public ArrayList<RentalDto> IterableDBRentalsToArrayListObjectRentals(Iterable<DBRentals> rentals) throws IOException{
        ArrayList<RentalDto> res = new ArrayList<>(); 

        for (DBRentals dbRentals : rentals) {
            res.add(new RentalDto( dbRentals.getName(),
                                    dbRentals.getSurface(),
                                    dbRentals.getPrice(),
                                    dbRentals.getPicture(),
                                    dbRentals.getDescription(),
                                    // dbRentals.getOwner_id(),
                                    dbRentals.getCreated_at(),
                                    dbRentals.getUpdated_at()));
        }
        return res;
    }
}
