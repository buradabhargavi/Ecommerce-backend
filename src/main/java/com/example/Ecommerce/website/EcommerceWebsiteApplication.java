package com.example.Ecommerce.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class EcommerceWebsiteApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(EcommerceWebsiteApplication.class, args);
	}

}
