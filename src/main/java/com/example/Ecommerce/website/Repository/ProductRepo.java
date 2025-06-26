package com.example.Ecommerce.website.Repository;

import com.example.Ecommerce.website.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByPartnerId(Long partnerId);

    List<Product> findByPartner_Id(Long id);

    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%', :keyword, '%')")
    List<Product> searchByName(@Param("keyword") String keyword);
}