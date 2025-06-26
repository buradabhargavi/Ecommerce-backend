package com.example.Ecommerce.website.Services;

import com.example.Ecommerce.website.Dto.LoginDto;
import com.example.Ecommerce.website.Entity.User;
import com.example.Ecommerce.website.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired private UserRepo userRepo;
    @Autowired
    private JWTService jwtService;
    @Autowired
    AuthenticationManager authmanager;
    public LoginDto verify(User user){
      //  System.out.println(user.getUsername()+user.getPassword()+user.getRole());
        Authentication authentication;
        try{
           // System.out.println("this is authentication");
             authentication =
                    authmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
          //  System.out.println("this is authentication"+authentication);
        } catch (Exception e) {
            throw new RuntimeException("Invalid cred");
        }
        if(authentication.isAuthenticated()){
            String token = jwtService.generateToken(user.getUsername());

            // Fetch user details to get role
            User userFromDb = userRepo.findByUsername(user.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));
           //  System.out.println(userFromDb);
            return new LoginDto(token, userFromDb.getRole(),userFromDb.getId());
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }

}
