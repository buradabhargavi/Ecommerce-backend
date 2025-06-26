package com.example.Ecommerce.website.Services;

import com.example.Ecommerce.website.Dto.ProductDto;
import com.example.Ecommerce.website.Entity.Partner;
import com.example.Ecommerce.website.Entity.Product;
import com.example.Ecommerce.website.Entity.User;
import com.example.Ecommerce.website.Repository.PartnerRepo;
import com.example.Ecommerce.website.Repository.ProductRepo;
import com.example.Ecommerce.website.Repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {
    @Autowired  private ProductRepo productRepo;
    @Autowired  private ModelMapper modelMapper;
    @Autowired private UserRepo userRepo;
    @Autowired private PartnerRepo partnerRepo;

    public  Product addProd(Product product){
        User user = getUserFromAuth();
        Partner partner = partnerRepo.findByUserId(user.getId());
        product.setPartner(partner);
        return productRepo.save(product);
    }

    public List<ProductDto> getAllProd(){
        List<Product> allProd = productRepo.findAll();
        return allProd.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
    }


    public List<ProductDto> getProdByPartnerId(){
        User user = getUserFromAuth();
        Partner partner = partnerRepo.findByUserId(user.getId());
        List<Product> allProd = productRepo.findByPartner_Id(partner.getId());
        return allProd.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
    }


//    public List<ProductDto> getAllProd(long partnerId){
//        List<Product> allProd = productRepo.findByPartnerId(partnerId);
//        return allProd.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
//    }


    public Product updateProduct(Long productId, Product updatedProduct, Long partnerId) {
        Product existingProduct = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        return productRepo.save(existingProduct);
    }


    public void deleteProduct(Long productId) {
        if (!productRepo.existsById(productId)) {
            throw new RuntimeException("Product not found");
        }
        productRepo.deleteById(productId);
    }

    public  List<ProductDto> search(String name){
        List<Product> items = productRepo.searchByName(name);
        return items.stream().map(item -> modelMapper.map(item,ProductDto.class)).collect(Collectors.toList());

    }

    private User getUserFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepo.findByUsername(username).orElseThrow();
    }

}
