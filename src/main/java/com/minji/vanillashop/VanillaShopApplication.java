package com.minji.vanillashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VanillaShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(VanillaShopApplication.class, args);
    }

}
