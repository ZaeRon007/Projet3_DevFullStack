package com.chatop.model.responses;

import com.chatop.model.DBRentals;

import lombok.Data;

@Data
public class JwtResponse_Rentals {
    Iterable<DBRentals> rentals;

    public JwtResponse_Rentals(Iterable<DBRentals> iterable){
        this.rentals = iterable;
    }
}
