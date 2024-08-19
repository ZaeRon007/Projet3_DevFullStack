package com.chatop.dto;

import java.text.ParseException;
import java.util.Optional;
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
    // Resource picture;
    String picture;
    String description;
    int owner_id;
    String created_at;
    String updated_at;

    public RentalDto(){}
    
    public RentalDto(   int id,
                        String name,
                        int surface,
                        int price,
                        // Resource picture,
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


    public RentalDto DBRentalToObjectRental( DBRentals  dbRental) throws ParseException{
        return new RentalDto(   dbRental.getId(),
                                dbRental.getName(), 
                                dbRental.getSurface(),
                                dbRental.getPrice(),
                                // getRentalImage(dbRental.getPicture()),
                                dbRental.getPicture(),
                                dbRental.getDescription(), 
                                dbRental.getOwner_id(),
                                dbRental.getCreated_at(), 
                                dbRental.getUpdated_at());
    }

    public DBRentals ObjectRentalToDBRental( RentalDto RentalDto){
        return new DBRentals(   RentalDto.getId(),
                                RentalDto.getName(), 
                                RentalDto.getSurface(),
                                RentalDto.getPrice(),
                                RentalDto.getPicture().toString(),
                                RentalDto.getDescription(), 
                                RentalDto.getOwner_id(),
                                RentalDto.getCreated_at(), 
                                RentalDto.getUpdated_at());
    }

    public DBRentals OptionnalDBRentalsToDBRentals(Optional<DBRentals> rentals){
        return new DBRentals(   rentals.get().getId(),
                                rentals.get().getName(),
                                rentals.get().getSurface(),
                                rentals.get().getPrice(),
                                rentals.get().getPicture(),
                                rentals.get().getDescription(),
                                rentals.get().getOwner_id(),
                                rentals.get().getCreated_at(),
                                rentals.get().getUpdated_at());
    }


    // private Resource getRentalImage(String path){
    //     try {
    //         Path filepath = Paths.get(path);
    //         Resource resource = new UrlResource(filepath.toUri());
            
    //         if(resource.exists() || resource.isReadable()){
    //             return resource;
    //         }
    //     } catch (Exception e) {
    //         System.out.printf("Erreur: %s\n",e);
    //     }
    //     return null;
    // }
}
