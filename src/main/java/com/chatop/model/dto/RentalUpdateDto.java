package com.chatop.model.dto;

import java.math.BigDecimal;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class RentalUpdateDto {

    String name;
    BigDecimal surface;
    BigDecimal price;
    String description;

    public RentalUpdateDto(){}

    public RentalUpdateDto(  String name,
                        BigDecimal surface,
                        BigDecimal price,
                        String description){
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.description = description;
    }
}
