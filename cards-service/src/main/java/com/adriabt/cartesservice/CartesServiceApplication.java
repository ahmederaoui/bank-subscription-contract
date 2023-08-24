package com.adriabt.cartesservice;

import com.adriabt.cartesservice.security.RsaKeyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication

@EnableConfigurationProperties(RsaKeyConfig.class)
public class CartesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartesServiceApplication.class, args);
    }

}
