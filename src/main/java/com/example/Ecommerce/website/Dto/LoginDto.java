package com.example.Ecommerce.website.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginDto {
        private String token;
        private String role;
        private long user_id;
}
