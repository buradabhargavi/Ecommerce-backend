package com.example.Ecommerce.website.Services;


import com.example.Ecommerce.website.Dto.PartnerDTO;
import com.example.Ecommerce.website.Entity.Partner;
import com.example.Ecommerce.website.Entity.User;
import com.example.Ecommerce.website.Repository.PartnerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnerService {
    @Autowired
    private PartnerRepo
            partnerRepo;
    @Autowired private ModelMapper modelMapper;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public Partner createPartner(Partner partner) {
        User user = partner.getUser();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        partner.setUser(user);
        return partnerRepo.save(partner);
    }

   public Partner getPartnerById(long id){
        Partner partner = partnerRepo.findById(id).orElseThrow(() -> new RuntimeException("Partner not found with id: "+id));
        return partner;
   }
   public Partner updatePartner(long id,Partner partner){
        Partner updatedPartner = partnerRepo.findById(id) .orElseThrow(() -> new RuntimeException("Partner not found with id: " + id));
        updatedPartner.setName(partner.getName());
        updatedPartner.setProducts(partner.getProducts());
        updatedPartner.setUser(partner.getUser());
        return updatedPartner;
   }
    public void deletePartner(Long id) {
        partnerRepo.deleteById(id);
    }

    public  List<PartnerDTO> getPartnersList(){
        List<Partner> partners = partnerRepo.findAll();
        return partners.stream().map(partner -> {
            PartnerDTO dto = modelMapper.map(partner, PartnerDTO.class);
            dto.setUsername(partner.getUser().getUsername());
            dto.setEmail(partner.getUser().getEmail());
            return dto;
        }).collect(Collectors.toList());
    }
}