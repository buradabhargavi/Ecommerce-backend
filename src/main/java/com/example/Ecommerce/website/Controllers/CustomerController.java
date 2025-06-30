package com.example.Ecommerce.website.Controllers;

import com.example.Ecommerce.website.Dto.CartDto;
import com.example.Ecommerce.website.Dto.ProductDto;
import com.example.Ecommerce.website.Entity.Cart;
import com.example.Ecommerce.website.Entity.Customer;
import com.example.Ecommerce.website.Entity.User;
import com.example.Ecommerce.website.Repository.CustomerRepo;
import com.example.Ecommerce.website.Repository.ProductRepo;
import com.example.Ecommerce.website.Services.CartService;
import com.example.Ecommerce.website.Services.CustomerService;
import com.example.Ecommerce.website.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ProductRepo productRepo;
    @Autowired private CartService cartService;
    @Autowired private CustomerService customerService;
    @Autowired private ProductService productService;

    @PostMapping("/signup")
    public Customer signup(@RequestBody Customer customer){
        return customerService.customerSignup(customer);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProd(){
        List<ProductDto> listOfProd = productService.getAllProd();
        return new ResponseEntity<>(listOfProd, HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public  List<Customer> getUsers(){
        List<Customer> customer = customerService.getCustomer();
        return customer;
    }
    @GetMapping("/getUser/{id}")
    public  Customer getUser(@PathVariable long id){
        Customer customer = customerService.getCustomer(id);
        return customer;
    }
    @PostMapping("/add-to-cart")
    public ResponseEntity<Cart> addToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        Cart item = cartService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartDto>> viewCart(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getUserCart(userId));
    }
    @PutMapping("/cart")
    public ResponseEntity<Cart> updateCartQuantity(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        Cart updatedCart = cartService.updateCartQuantity(userId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

//    @DeleteMapping("/cart")
//    public ResponseEntity<String> deleteCartItem(@RequestParam Long cartId) {
//        cartService.deleteCartItem(cartId);
//        return ResponseEntity.ok("Cart item deleted successfully");
//    }

    //    @PostMapping("/addUser")
//    public User addUser(@RequestBody User user){
//        return customerService.addUser(user);
//    }


//deleteByUserId
    @DeleteMapping("/cart/{userId}")
    public  String checkout(@PathVariable Long userId){
       String res = cartService.checkout(userId);
       return res;
    }

    //search products

    @GetMapping("/search")
    public  ResponseEntity<List<ProductDto>> search(@RequestParam String name){
        List<ProductDto> searchProd = productService.search(name);
        return new ResponseEntity<>(searchProd,HttpStatus.OK);
    }


}
