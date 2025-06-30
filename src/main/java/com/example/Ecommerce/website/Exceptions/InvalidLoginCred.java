package com.example.Ecommerce.website.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidLoginCred extends RuntimeException {

    public InvalidLoginCred() {
        super("Invalid login credentials");
    }
}
