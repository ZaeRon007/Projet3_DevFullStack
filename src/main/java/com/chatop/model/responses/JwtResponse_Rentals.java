package com.chatop.model.responses;

import java.util.ArrayList;

import com.chatop.model.dto.RentalDto;

import lombok.Data;

@Data
public class JwtResponse_Rentals {
    ArrayList<RentalDto> rentals;

    public JwtResponse_Rentals( ArrayList<RentalDto> iterable){
        this.rentals = iterable;
    }
}
