package com.example.Ecommerce.website.Repository;

import com.example.Ecommerce.website.Entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepo extends JpaRepository<Partner, Long> {
    public Partner findByUserId(long id);
}