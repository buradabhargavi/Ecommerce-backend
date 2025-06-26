package com.example.Ecommerce.website.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long productId;
    private String productName;
    private Double productPrice;
    private String productDescription;
    private int quantity;
}
