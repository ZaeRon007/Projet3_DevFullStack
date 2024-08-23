package com.chatop.model.responses;

import java.util.ArrayList;

import com.chatop.model.dto.RentalSDto;

import lombok.Data;

@Data
public class ArrayListOfDtoRentals {
    ArrayList<RentalSDto> rentals;

    public ArrayListOfDtoRentals( ArrayList<RentalSDto> iterable){
        this.rentals = iterable;
    }
}
