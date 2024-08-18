package com.chatop.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.DTO.RentalDto;
import com.chatop.model.DBRentals;
import com.chatop.model.JwtMessage;
import com.chatop.model.JwtRentals;
import com.chatop.repository.DBRentalsRepository;

@Service
public class DBRentalsService {
    
    @Autowired
    private DBRentalsRepository DBRentalsRepository;

    public ResponseEntity<?> getRentals(){
        return ResponseEntity.ok().body( new JwtRentals(DBRentalsRepository.findAll()));
    }

    public Optional<DBRentals> getRentalById(Integer Id){
        return DBRentalsRepository.findById(Id);
    }

    public void addRentals(DBRentals DBRentals){
        DBRentalsRepository.save(DBRentals);
    }

    public void removeRental(DBRentals DBRentals){
        DBRentalsRepository.delete(DBRentals);
    }

    public ResponseEntity<?> updateRental(String id, String name, int surface, int price, String description){
        
        DBRentals rental = new DBRentals(   name,
                                            surface,
                                            price,
                                            DBRentalsRepository.findById(Integer.parseInt(id)).getPicture(),
                                            description,
                                            DBRentalsRepository.findById(Integer.parseInt(id)).getCreated_at(),
                                            LocalDate.now().toString());
         
        rental.setId(Integer.parseInt(id));
        DBRentalsRepository.save(rental);

        return ResponseEntity.ok().body(new JwtMessage("Rental updated !"));
    }
        

    public ResponseEntity<?> getRentalsById(int id) {
        return ResponseEntity.ok().body(getRentalById(id));
    }

    public ResponseEntity<?> createRental(  String name,
                                            int surface,
                                            int price,
                                            String description,
                                            MultipartFile picture){
        String picturePath = savePicture(picture);
        DBRentals rental = new DBRentals(   name,
                                            surface,
                                            price,
                                            picturePath,
                                            description,
                                            LocalDate.now().toString(),
                                            LocalDate.now().toString());

        addRentals(rental);

        return ResponseEntity.ok().body(new JwtMessage("Rental created !"));
    }

    private String savePicture(MultipartFile picture) {
        // Vous pouvez définir le répertoire de stockage pour les images
        String directory = "uploads/";

        try {
            // Générer un nom de fichier unique
            String filename = picture.getOriginalFilename();
            Path filepath = Paths.get(directory, filename);

            // Sauvegarder l'image sur le disque
            Files.write(filepath, picture.getBytes());

            // Retourner le chemin du fichier (relatif ou absolu selon vos besoins)
            return filepath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store the picture", e);
        }
    }
}
