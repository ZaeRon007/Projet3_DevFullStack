package com.chatop.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.chatop.model.DBRentals;
import com.chatop.model.dto.RentalMDto;
import com.chatop.model.dto.RentalSDto;
import com.chatop.model.dto.RentalUpdateDto;
import com.chatop.model.responses.ArrayListOfDtoRentals;
import com.chatop.model.responses.simpleMessage;
import com.chatop.repository.DBRentalsRepository;
import com.chatop.repository.DBUserRepository;

@Service
public class DBRentalsService {
    
    @Autowired
    private DBRentalsRepository DBRentalsRepository;

    @Autowired
    private DBUserRepository dbUserRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private RentalSDto rentalSDto;

    @Value("${picture.directory}")
    private String directory;

    public ResponseEntity<?> getRentals() throws IOException{
        return ResponseEntity.ok().body( new ArrayListOfDtoRentals(rentalSDto.IterableDBRentalsToArrayListObjectRentals(DBRentalsRepository.findAll())));
    }

    public void addRentals(DBRentals DBRentals){
        DBRentalsRepository.save(DBRentals);
    }

    public void removeRental(DBRentals DBRentals){
        DBRentalsRepository.delete(DBRentals);
    }

    public ResponseEntity<?> updateRental(String id, RentalUpdateDto rentalUpdateDto){
        DBRentals dbOne = DBRentalsRepository.findById(Integer.parseInt(id));
        dbOne.setName(rentalUpdateDto.getName());
        dbOne.setSurface(rentalUpdateDto.getSurface());
        dbOne.setPrice(rentalUpdateDto.getPrice());
        dbOne.setDescription(rentalUpdateDto.getDescription());
        dbOne.setUpdatedAt(new TimeService().getTime());

        DBRentalsRepository.save(dbOne);

        return ResponseEntity.ok().body(new simpleMessage("Rental updated !"));
    }
        

    public ResponseEntity<?> getRentalsById(int id) throws ParseException, IOException {
        return ResponseEntity.ok().body((DBRentalsRepository.findById(id)).ToRentalSDto());
    }

    public ResponseEntity<?> createRental( RentalMDto rentalMDto){
        RentalSDto rentalSDto = rentalMDto.ToSdto();

        try {
            String filename = rentalSDto.getPicture();
            Path filepath = Paths.get(directory, filename);
            String UrlPicture = s3Service.uploadFile(filepath, filename);

            DBRentals rentalToAdd = new DBRentals(  rentalSDto.getName(),
                                                    rentalSDto.getSurface(),
                                                    rentalSDto.getPrice(),
                                                    UrlPicture,
                                                    rentalSDto.getDescription(),
                                                    getAuthenticatedUserId(),
                                                    new TimeService().getTime(),
                                                    new TimeService().getTime());
            addRentals(rentalToAdd);
            return ResponseEntity.ok().body(new simpleMessage("Rental created !"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad Request: " + e);
        }
    }

    private int getAuthenticatedUserId(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return dbUserRepository.findByEmail(username).getId();
    }
}
