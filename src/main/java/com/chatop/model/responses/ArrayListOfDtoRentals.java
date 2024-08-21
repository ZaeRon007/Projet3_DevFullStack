package com.chatop.model.responses;

import java.util.ArrayList;

import com.chatop.model.dto.RentalDto;

import lombok.Data;

@Data
public class ArrayListOfDtoRentals {
    ArrayList<RentalDto> rentals;

    public ArrayListOfDtoRentals( ArrayList<RentalDto> iterable){
        this.rentals = iterable;
    }
}
