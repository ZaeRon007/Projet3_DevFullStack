package com.chatop.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.chatop.model.DBRentals;
import com.chatop.model.JwtMessage;
import com.chatop.model.JwtRentals;
import com.chatop.repository.DBRentalsRepository;
import com.chatop.services.DBRentalsService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api")
public class DBRentalController {

    @Autowired
    private DBRentalsService DBRentalsService;

    @Autowired
    private DBRentalsRepository dbRentalsRepository;

    // @Autowired
    // private userService userService;

    @GetMapping("/rentals")
    public ResponseEntity<?> getRentals() {
        return ResponseEntity.ok().body( new JwtRentals(DBRentalsService.getRentals()));
    }

    @GetMapping("/rentals/{id}")
    public ResponseEntity<?> getRentalsById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(DBRentalsService.getRentalById(id));
    }

    @PostMapping("/rentals")
    public ResponseEntity<?> createRental(  @RequestParam("name") String name,
                                            @RequestParam("surface") int surface,
                                            @RequestParam("price") int price,
                                            @RequestParam("description") String description,
                                            @RequestParam("picture") MultipartFile picture){
        // Stocker l'image sur le disque et obtenir le chemin
        String picturePath = savePicture(picture);

        // Créer une instance de DBRentals
        DBRentals rental = new DBRentals();
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        rental.setDescription(description);
        rental.setPicture(picturePath);
        rental.setCreated_at(LocalDate.now().toString());
        rental.setUpdated_at(LocalDate.now().toString());

        // Appel du service pour sauvegarder la location
        DBRentalsService.addRentals(rental);

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
    
    @PutMapping("/rentals/{id}")
    public ResponseEntity<?> updateRental(  @PathVariable String id, 
                                            @RequestParam("name") String name,
                                            @RequestParam("surface") int surface,
                                            @RequestParam("price") int price,
                                            @RequestParam("description") String description) {
        DBRentals rental = new DBRentals();

        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        rental.setDescription(description);
        rental.setUpdated_at(LocalDate.now().toString());
        rental.setCreated_at( dbRentalsRepository.findById(Integer.parseInt(id)).getCreated_at());
        rental.setPicture( dbRentalsRepository.findById(Integer.parseInt(id)).getPicture());

        DBRentalsService.updateRental(id, rental);

        return ResponseEntity.ok().body(new JwtMessage("Rental updated !"));

    }   
}
