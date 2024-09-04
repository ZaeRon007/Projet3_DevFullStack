package com.chatop.services;

import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.chatop.model.RentalEntity;
import com.chatop.model.dto.RentalMDto;
import com.chatop.model.dto.RentalSDto;
import com.chatop.model.dto.RentalUpdateDto;
import com.chatop.model.responses.simpleMessage;
import com.chatop.repository.RentalsRepository;
import com.chatop.repository.UserRepository;

@Service
public class RentalsService {
    
    @Autowired
    private RentalsRepository DBRentalsRepository;

    @Autowired
    private UserRepository dbUserRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private RentalSDto rentalSDto;

    @Value("${picture.directory}")
    private String directory;

    /**
     * Get all rentals from database
     * @return ArrayList<RentalSDto> an arraylist of all rentals
     * @throws IOException
     */
    public ArrayList<RentalSDto> getRentals() throws IOException{
        return rentalSDto.IterableDBRentalsToArrayListObjectRentals(DBRentalsRepository.findAll());
    }

    /**
     * save a new rental to database
     * @param rental a rental Entity
     */
    public void addRentals(RentalEntity rental){
        DBRentalsRepository.save(rental);
    }

    /**
     * remove en rental from database
     * @param rental a rental Entity
     */
    public void removeRental(RentalEntity rental){
        DBRentalsRepository.delete(rental);
    }

    /**
     * update a rental by an id from database
     * @param id of the rental to update
     * @param rentalUpdateDto new informations to apply
     */
    public void updateRental(String id, RentalUpdateDto rentalUpdateDto){
        RentalEntity dbOne = DBRentalsRepository.findById(Integer.parseInt(id));
        dbOne.setName(rentalUpdateDto.getName());
        dbOne.setSurface(rentalUpdateDto.getSurface());
        dbOne.setPrice(rentalUpdateDto.getPrice());
        dbOne.setDescription(rentalUpdateDto.getDescription());
        dbOne.setUpdatedAt(new TimeService().getTime());

        DBRentalsRepository.save(dbOne);
    }
        

    /**
     * get a rental by an id from database
     * @param id id of the rental
     * @return RentalSDto
     * @throws ParseException
     * @throws IOException
     */
    public RentalSDto getRentalsById(int id) throws ParseException, IOException {
        return DBRentalsRepository.findById(id).ToRentalSDto();
    }

    /**
     * create a new rental from database
     * @param rentalMDto rental to create
     * @return simpleMessage 
     */
    public simpleMessage createRental( RentalMDto rentalMDto){
        RentalSDto rentalSDto = rentalMDto.ToSdto();

        try {
            String filename = rentalMDto.getPicture().getOriginalFilename();
            Path filepath = Paths.get(directory, filename);
            String UrlPicture = s3Service.uploadFile(filepath, filename);

            RentalEntity rentalToAdd = new RentalEntity(  rentalSDto.getName(),
                                                    rentalSDto.getSurface(),
                                                    rentalSDto.getPrice(),
                                                    UrlPicture,
                                                    rentalSDto.getDescription(),
                                                    getAuthenticatedUserId(),
                                                    new TimeService().getTime(),
                                                    new TimeService().getTime());
            addRentals(rentalToAdd);
            return new simpleMessage("Rental created !");
        } catch (Exception e) {
            return new simpleMessage("Bad Request: " + e);
        }
    }

    /**
     * return authenticated user id 
     * @return Id id of the authenticated user
     */
    private int getAuthenticatedUserId(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return dbUserRepository.findByEmail(username).getId();
    }
}
