package com.example.Ecommerce.website.Repository;

import com.example.Ecommerce.website.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
