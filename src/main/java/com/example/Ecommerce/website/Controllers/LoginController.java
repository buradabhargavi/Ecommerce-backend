package com.example.Ecommerce.website.Controllers;

import com.example.Ecommerce.website.Dto.LoginDto;
import com.example.Ecommerce.website.Entity.User;
import com.example.Ecommerce.website.Services.LoginService;
import com.example.Ecommerce.website.Services.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    @Autowired  private LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody User user){
        LoginDto response = loginService.verify(user);
        return ResponseEntity.ok(response);
    }
}
