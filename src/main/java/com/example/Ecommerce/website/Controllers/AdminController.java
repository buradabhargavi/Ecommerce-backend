package com.example.Ecommerce.website.Controllers;

import com.example.Ecommerce.website.Dto.PartnerDTO;
import com.example.Ecommerce.website.Entity.Partner;
import com.example.Ecommerce.website.Services.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PartnerService partnerService;

    @PostMapping("/partners")
    public ResponseEntity<Partner> createPartner(@RequestBody Partner partner) {
        return ResponseEntity.ok(partnerService.createPartner(partner));
    }

    @GetMapping("/partners")
    public  ResponseEntity<List<PartnerDTO>> getAllPartners(){
        List<PartnerDTO> partnersList = partnerService.getPartnersList();
        return new ResponseEntity<>(partnersList,HttpStatus.OK);
    }

    @GetMapping("/partners/{id}")
    public  ResponseEntity<Partner> getPartnerById(@PathVariable long id){
        Partner parnterDetails = partnerService.getPartnerById(id);
        return new ResponseEntity<>(parnterDetails,HttpStatus.OK);
    }

    @DeleteMapping("/partners/{id}")
    public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        partnerService.deletePartner(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/partners/{id}")
        public ResponseEntity<Partner>  updatePartner(@PathVariable long id, @RequestBody Partner partner){
            Partner UpdatedPartner = partnerService.updatePartner(id,partner);
            return new ResponseEntity<>(UpdatedPartner,HttpStatus.OK);
        }

}
