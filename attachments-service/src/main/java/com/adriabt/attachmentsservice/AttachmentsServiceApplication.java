package com.adriabt.attachmentsservice;

import com.adriabt.attachmentsservice.security.RsaKeyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(RsaKeyConfig.class)
public class AttachmentsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttachmentsServiceApplication.class, args);
    }

}
