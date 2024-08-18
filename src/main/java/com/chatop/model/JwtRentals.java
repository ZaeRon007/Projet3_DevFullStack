package com.chatop.model;

import lombok.Data;

@Data
public class JwtRentals {
    Iterable<DBRentals> rentals;

    public JwtRentals(Iterable<DBRentals> iterable){
        this.rentals = iterable;
    }
}
