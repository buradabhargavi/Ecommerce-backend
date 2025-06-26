package com.example.Ecommerce.website.Controllers;

import com.example.Ecommerce.website.Dto.ProductDto;
import com.example.Ecommerce.website.Entity.Partner;
import com.example.Ecommerce.website.Entity.Product;
import com.example.Ecommerce.website.Entity.User;
import com.example.Ecommerce.website.Repository.PartnerRepo;
import com.example.Ecommerce.website.Repository.ProductRepo;
import com.example.Ecommerce.website.Repository.UserRepo;
import com.example.Ecommerce.website.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/partner")
public class PartnerController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired private PartnerRepo partnerRepo;
  @Autowired private ProductService productService;


    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAddedProds(){
        List<ProductDto> listOfProd = productService.getProdByPartnerId();
        return new ResponseEntity<>(listOfProd,HttpStatus.OK);
    }
    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedproduct = productService.addProd(product);
        return ResponseEntity.ok(addedproduct);
    }


    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,
                                                 @RequestBody Product updatedProduct,
                                                 @RequestParam Long partnerId) {
        Product updated = productService.updateProduct(productId, updatedProduct, partnerId);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }


}

