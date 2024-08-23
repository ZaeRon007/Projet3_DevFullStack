package com.chatop.model.dto;

import java.math.BigDecimal;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
@Configuration
public class RentalMDto {
    String name;
    BigDecimal surface;
    BigDecimal price;
    MultipartFile picture;
    String description;

    public RentalSDto ToSdto(){
        return new RentalSDto(  this.getName(), 
                                this.getSurface(),
                                this.getPrice(),
                                this.getPicture().getOriginalFilename(),
                                this.getDescription());
    }
}
