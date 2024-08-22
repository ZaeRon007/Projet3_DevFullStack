package com.chatop.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.FloatBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.chatop.model.DBRentals;
import com.chatop.model.dto.RentalDto;
import com.chatop.model.responses.ArrayListOfDtoRentals;
import com.chatop.model.responses.simpleMessage;
import com.chatop.repository.DBRentalsRepository;

@Service
public class DBRentalsService {
    
    @Autowired
    private DBRentalsRepository DBRentalsRepository;

    @Autowired
    private RentalDto rentalDto;

    @Autowired
    private S3Service s3Service;

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

    public ResponseEntity<?> updateRental(String id, String name, DecimalFormat surface, DecimalFormat price, String description){
        DBRentals dbone = DBRentalsRepository.findById(Integer.parseInt(id));
        dbone.setName(name);
        dbone.setSurface(surface);
        dbone.setPrice(price);
        dbone.setDescription(description);

        DBRentalsRepository.save(dbone);

        return ResponseEntity.ok().body(new simpleMessage("Rental updated !"));
    }
        

    public ResponseEntity<?> getRentalsById(int id) throws ParseException, IOException {
        return ResponseEntity.ok().body(rentalDto.DBRentalToObjectRental(DBRentalsRepository.findById(id)));
    }

    public ResponseEntity<?> createRental(  String name,
                                            BigDecimal surface,
                                            BigDecimal price,
                                            String description,
                                            MultipartFile picture){
        String UrlPicture = "";
        try {
            String filename = picture.getOriginalFilename();
            Path filepath = Paths.get(directory, filename);

            UrlPicture = s3Service.uploadFile(filepath, filename);
            DecimalFormat dfSurface = new DecimalFormat("#.##");
            
            // dfSurface= new DecimalFormat(surface.toString());
            DecimalFormat dfPrice = new DecimalFormat("#.##");
            
            // dfPrice= new DecimalFormat(price.toString());

            System.out.printf("name: %s surface: %s price: %s UrlPicture: %s\n description: %s\n", name, surface, price, UrlPicture, description);
            DBRentals rental = new DBRentals(   name,
                                                surface,
                                                price,
                                                UrlPicture,
                                                description,
                                                new Timestamp(new Date().getTime()),
                                                new Timestamp(new Date().getTime()));
            addRentals(rental);
            return ResponseEntity.ok().body(new simpleMessage("Rental created !"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
