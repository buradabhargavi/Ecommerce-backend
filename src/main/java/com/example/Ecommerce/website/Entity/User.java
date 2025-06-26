package com.example.Ecommerce.website.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.validation.constraints.Email;

@Entity
@Data
public class User {
        @Id
        @GeneratedValue
        private Long id;
        @Column(unique = true)
        private String username;
        @Column(nullable = false, unique = true)
        @Email(message="Email is not valid")
        private String email;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;
        private String role;
}
