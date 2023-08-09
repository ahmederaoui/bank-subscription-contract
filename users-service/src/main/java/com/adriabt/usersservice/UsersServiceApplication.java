package com.adriabt.usersservice;

import com.adriabt.usersservice.entities.Agent;
import com.adriabt.usersservice.entities.AppUser;
import com.adriabt.usersservice.entities.Subscriber;
import com.adriabt.usersservice.services.impl.AgentServiceImpl;
import com.adriabt.usersservice.services.impl.SubscriberServiceImpl;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.time.NtpTimeProvider;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.UnknownHostException;

@SpringBootApplication

public class UsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner( AgentServiceImpl agentService,  SubscriberServiceImpl subscriberService){
        return args -> {
            Agent agent = new Agent();
            agent.setFirstname("ahmed");
            agent.setLastname("eraoui");
            agent.setEmail("eraoui@gmail.com");
            agent.setPhone(0601734046L);
            agent.setAgency("ouazzane");
            agent.setPassword("eraoui!@#4");
            Subscriber subscriber = new Subscriber();
            subscriber.setFirstname("ahmed");
            subscriber.setLastname("eraoui");
            subscriber.setEmail("eraoui@gmail.com");
            subscriber.setPhone(0601734046L);
            subscriber.setNationality("maroc");
            subscriber.setCin("GM4231223");
            subscriber.setPassword("faasgfsdg");
            agentService.createAgent(agent);
            subscriberService.createSubscriber(subscriber);
        };
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public HashingAlgorithm hashingAlgorithm() {
        return HashingAlgorithm.SHA256;
    }

}
