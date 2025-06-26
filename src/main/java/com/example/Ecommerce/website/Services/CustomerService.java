package com.example.Ecommerce.website.Services;

import com.example.Ecommerce.website.Entity.Customer;
import com.example.Ecommerce.website.Entity.User;
import com.example.Ecommerce.website.Repository.CustomerRepo;
import com.example.Ecommerce.website.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired CustomerRepo customerRepo;
    @Autowired
    UserRepo userRepo;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public Customer customerSignup(Customer customer){
        User user = customer.getUser();
        user.setRole("CUSTOMER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return customerRepo.save(customer);
    }
//    public User addUser(User user){
//        try {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            return userRepo.save(user);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    public Customer getCustomer(long id){
        return  customerRepo.findById(id).orElseThrow();
    }

    public List<Customer> getCustomer(){
        return  customerRepo.findAll();
    }


}
