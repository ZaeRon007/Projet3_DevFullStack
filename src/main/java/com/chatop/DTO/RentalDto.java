package com.chatop.DTO;

import java.text.ParseException;
import org.springframework.context.annotation.Configuration;
import com.chatop.model.DBRentals;

import lombok.Data;

@Data
@Configuration
public class RentalDto {
    int id;
    String name;
    int surface;
    int price;
    String picture;
    String description;
    String created_at;
    String updated_at;

    public RentalDto(){}
    
    public RentalDto(   int id,
                        String name,
                        int surface,
                        int price,
                        String picture,
                        String description,
                        String created_at,
                        String updated_at){
        
        this.id = id;
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public RentalDto DBRentalToObjectRental( DBRentals  dbRental) throws ParseException{
        return new RentalDto(   dbRental.getId(),
                                dbRental.getName(), 
                                dbRental.getSurface(),
                                dbRental.getPrice(),
                                dbRental.getPicture(),
                                dbRental.getDescription(), 
                                dbRental.getCreated_at(), 
                                dbRental.getUpdated_at());
    }

    public DBRentals ObjectRentalToDBRental( RentalDto RentalDto){
        return new DBRentals(   RentalDto.getName(), 
                                RentalDto.getSurface(),
                                RentalDto.getPrice(),
                                RentalDto.getPicture(),
                                RentalDto.getDescription(), 
                                RentalDto.getCreated_at(), 
                                RentalDto.getUpdated_at());
    }
}
