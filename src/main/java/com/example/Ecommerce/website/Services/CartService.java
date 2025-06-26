package com.example.Ecommerce.website.Services;

import com.example.Ecommerce.website.Dto.CartDto;
import com.example.Ecommerce.website.Entity.Cart;
import com.example.Ecommerce.website.Entity.Product;
import com.example.Ecommerce.website.Entity.User;
import com.example.Ecommerce.website.Repository.CartRepo;
import com.example.Ecommerce.website.Repository.ProductRepo;
import com.example.Ecommerce.website.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    public Cart addToCart(Long userId, Long productId, int quantity) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        Cart item = new Cart();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(quantity);

        return cartRepo.save(item);
    }

//    public List<Cart> getUserCart(Long userId) {
//        return cartRepo.findByUserId(userId);
//    }

    public List<CartDto> getUserCart(Long userId) {
        List<Cart> carts = cartRepo.findByUserId(userId);
        return carts.stream().map(cart -> new CartDto(
                cart.getProduct().getId(),
                cart.getProduct().getName(),
                cart.getProduct().getPrice(),
                cart.getProduct().getDescription(),
                cart.getQuantity()
        )).toList();
    }

//    public void deleteCartItem(Long cartId) {
//        if (!cartRepo.existsById(cartId)) {
//            throw new RuntimeException("Cart item not found");
//        }
//        cartRepo.deleteById(cartId);
//    }
//    public Cart updateCartQuantity(Long cartId, int quantity) {
//        Cart cart = cartRepo.findById(cartId)
//                .orElseThrow(() -> new RuntimeException("Cart item not found"));
//
//        cart.setQuantity(quantity);
//        return cartRepo.save(cart);
//    }

   @Transactional
    public String checkout(long id){
        try {
            cartRepo.deleteByUserId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "order submitted";
    }
}

