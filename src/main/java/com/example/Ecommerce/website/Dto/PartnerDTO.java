package com.example.Ecommerce.website.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDTO {
        private Long id;
        private String name;
        private String username;
        private String email;
}
