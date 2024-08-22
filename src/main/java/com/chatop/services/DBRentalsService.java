package com.chatop.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.model.DBRentals;
import com.chatop.model.dto.RentalDto;
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
    private RentalDto rentalDto;

    @Value("${picture.directory}")
    private String directory;

    public ResponseEntity<?> getRentals() throws IOException{
        return ResponseEntity.ok().body( new ArrayListOfDtoRentals(rentalDto.IterableDBRentalsToArrayListObjectRentals(DBRentalsRepository.findAll())));
    }

    public void addRentals(DBRentals DBRentals){
        DBRentalsRepository.save(DBRentals);
    }

    public void removeRental(DBRentals DBRentals){
        DBRentalsRepository.delete(DBRentals);
    }

    public ResponseEntity<?> updateRental(String id, RentalDto rentalDto){
        DBRentals dbOne = DBRentalsRepository.findById(Integer.parseInt(id));
        dbOne.setName(rentalDto.getName());
        dbOne.setSurface(rentalDto.getSurface());
        dbOne.setPrice(rentalDto.getPrice());
        dbOne.setDescription(rentalDto.getDescription());
        dbOne.setUpdatedAt(new TimeService().getTime());

        DBRentalsRepository.save(dbOne);

        return ResponseEntity.ok().body(new simpleMessage("Rental updated !"));
    }
        

    public ResponseEntity<?> getRentalsById(int id) throws ParseException, IOException {
        return ResponseEntity.ok().body((DBRentalsRepository.findById(id)).ToRentalDto());
    }

    // public ResponseEntity<?> createRental( RentalDto rentalDto){
    public ResponseEntity<?> createRental(  String name,
                                            BigDecimal surface,
                                            BigDecimal price,
                                            MultipartFile picture,
                                            String description){
        String UrlPicture = "";
        try {
            String filename = picture.getOriginalFilename();
            Path filepath = Paths.get(directory, filename);
            UrlPicture = s3Service.uploadFile(filepath, filename);

            DBRentals rental = new DBRentals(   name,
                                                surface,
                                                price,
                                                UrlPicture,
                                                description,
                                                getAuthenticatedUserId(),
                                                new TimeService().getTime(),
                                                new TimeService().getTime());
            addRentals(rental);
            return ResponseEntity.ok().body(new simpleMessage("Rental created !"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    private int getAuthenticatedUserId(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return dbUserRepository.findByEmail(username).getId();
    }
}
