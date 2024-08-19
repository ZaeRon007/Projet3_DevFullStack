package com.chatop.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.dto.RentalDto;
import com.chatop.model.DBRentals;
import com.chatop.model.responses.JwtResponse_Message;
import com.chatop.model.responses.JwtResponse_RentalMessage;
import com.chatop.model.responses.JwtResponse_Rentals;
import com.chatop.repository.DBRentalsRepository;
import com.chatop.repository.DBUserRepository;

@Service
public class DBRentalsService {
    
    @Autowired
    private DBRentalsRepository DBRentalsRepository;

    @Autowired
    private DBUserRepository dbUserRepository;

    @Autowired
    private RentalDto rentalDto;

    private int previousGetRentalId = -1;

    public ResponseEntity<?> getRentals() throws IOException{
        return ResponseEntity.ok().body( new JwtResponse_Rentals(rentalDto.IterableDBRentalsToArrayListObjectRentals(DBRentalsRepository.findAll())));
    }

    public void addRentals(DBRentals DBRentals){
        DBRentalsRepository.save(DBRentals);
    }

    public void removeRental(DBRentals DBRentals){
        DBRentalsRepository.delete(DBRentals);
    }

    public ResponseEntity<?> updateRental(String id, String name, int surface, int price, String description){
        
        DBRentals rental = new DBRentals(   DBRentalsRepository.findById(Integer.parseInt(id)).getId(),
                                            name,
                                            surface,
                                            price,
                                            DBRentalsRepository.findById(Integer.parseInt(id)).getPicture(),
                                            description,
                                            DBRentalsRepository.findById(Integer.parseInt(id)).getOwner_id(),
                                            DBRentalsRepository.findById(Integer.parseInt(id)).getCreated_at(),
                                            LocalDate.now().toString());
         
        rental.setId(Integer.parseInt(id));
        DBRentalsRepository.save(rental);

        return ResponseEntity.ok().body(new JwtResponse_Message("Rental updated !"));
    }
        

    public ResponseEntity<?> getRentalsById(int id) throws ParseException, IOException {
        previousGetRentalId = id;
        return ResponseEntity.ok().body(rentalDto.DBRentalToObjectRental(DBRentalsRepository.findById(id)));
    }

    public ResponseEntity<?> createRental(  String name,
                                            int surface,
                                            int price,
                                            String description,
                                            MultipartFile picture){
        String picturePath = savePicture(picture);
        System.out.printf("picturepath: %s\n",picturePath);
        DBRentals rental = new DBRentals(   getANewId(),
                                            name,
                                            surface,
                                            price,
                                            picturePath,
                                            description,
                                            getAuthenticatedUserId(),
                                            LocalDate.now().toString(),
                                            LocalDate.now().toString());
        System.out.printf("picturepath from rental: %s\n",rental.getPicture());
        addRentals(rental);

        return ResponseEntity.ok().body(new JwtResponse_Message("Rental created !"));
    }

    private String savePicture(MultipartFile picture) {
        String directory = "uploads/";

        try {
            String filename = picture.getOriginalFilename();
            Path filepath = Paths.get(directory, filename);
            Files.write(filepath, picture.getBytes());
            return filepath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store the picture", e);
        }
    }

    private int getAuthenticatedUserId(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return dbUserRepository.findByEmail(username).getId();
    }

    private int getANewId(){
        int cursor = 1;
        while(DBRentalsRepository.existsById(cursor))
            cursor++;
        return cursor;
    }

    public ResponseEntity<?> postMessage(String entity) {
        return ResponseEntity.ok().body(new JwtResponse_RentalMessage(entity, getAuthenticatedUserId(), previousGetRentalId));
    }
}
