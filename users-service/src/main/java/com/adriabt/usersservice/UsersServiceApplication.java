package com.adriabt.usersservice;

import com.adriabt.usersservice.entities.Agent;
import com.adriabt.usersservice.entities.AppUser;
import com.adriabt.usersservice.entities.Subscriber;
import com.adriabt.usersservice.services.impl.AgentServiceImpl;
import com.adriabt.usersservice.services.impl.SubscriberServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }
    @Bean
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

}
