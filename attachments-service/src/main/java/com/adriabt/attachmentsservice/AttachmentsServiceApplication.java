package com.adriabt.attachmentsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AttachmentsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttachmentsServiceApplication.class, args);
    }

}
