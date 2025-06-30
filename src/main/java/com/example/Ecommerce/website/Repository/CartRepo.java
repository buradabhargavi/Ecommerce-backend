package com.example.Ecommerce.website.Repository;

import com.example.Ecommerce.website.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);
    void deleteByUserId(long userId);
    Cart findByUserIdAndProductId(Long userId, Long productId);
}
