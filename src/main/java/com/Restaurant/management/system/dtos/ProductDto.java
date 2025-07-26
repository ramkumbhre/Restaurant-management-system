package com.Restaurant.management.system.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private String price;

    private  String description;

    private byte[] returnedImg;

    private MultipartFile img;

    private Long categoryId;

    private String categoryName;
}
