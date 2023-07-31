package com.adriabt.usersservice.services.impl;

import com.adriabt.usersservice.entities.Agent;
import com.adriabt.usersservice.entities.Subscriber;
import com.adriabt.usersservice.enums.AppRole;
import com.adriabt.usersservice.exceptions.EmailExist;
import com.adriabt.usersservice.exceptions.MissingInformation;
import com.adriabt.usersservice.exceptions.SubscriberNotFound;
import com.adriabt.usersservice.repositories.SubscriberRepository;
import com.adriabt.usersservice.services.ISubscriberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriberServiceImpl implements ISubscriberService {
    private final SubscriberRepository subscriberRepository;
    @Override
    public Subscriber createSubscriber(Subscriber subscriber) throws EmailExist, MissingInformation {
        if (subscriber.getFirstname()!=null && subscriber.getLastname()!=null && subscriber.getEmail()!=null&& subscriber.getPassword()!=null){
            Optional<Subscriber> newSubscriber =subscriberRepository.findSubscriberByEmail(subscriber.getEmail());
            if (newSubscriber.isPresent()) throw new EmailExist(String.format("This email %s is already exist",subscriber.getEmail()));
            subscriber.setRole(AppRole.ABONNE);
            subscriber.setCreationDate(new Date());
            subscriber.setUpdateDate(new Date());
            return subscriberRepository.save(subscriber);
        }else throw new MissingInformation("Some important information is missing");
    }

    @Override
    public Page<Subscriber> findAllSubscribersByKeyword(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Subscriber> allByFirstnameOrLastnameOrEmailOrPhone = subscriberRepository.findAllByFirstnameOrLastnameOrEmailOrPhone(keyword, pageable);
        return allByFirstnameOrLastnameOrEmailOrPhone;
    }

    @Override
    public Subscriber findSubscriberByEmail(String email) throws SubscriberNotFound {
        return subscriberRepository.findSubscriberByEmail(email).orElseThrow(()->new SubscriberNotFound(String.format("The subscriber %s not found",email)));
    }

    @Override
    public Subscriber updateSubscriber(Subscriber subscriber) throws SubscriberNotFound, EmailExist {
        Subscriber updatedSubscriber = findSubscriberByEmail(subscriber.getEmail());
        if (subscriber.getFirstname()!=null) updatedSubscriber.setFirstname(subscriber.getFirstname());
        if (subscriber.getLastname()!=null) updatedSubscriber.setLastname(subscriber.getLastname());
        if (subscriber.getPhone()!=null) updatedSubscriber.setPhone(subscriber.getPhone());
        if (subscriber.getNationality()!=null) updatedSubscriber.setNationality(subscriber.getNationality());
        if (subscriber.getCin()!=null) updatedSubscriber.setCin(subscriber.getCin());
        if (subscriber.getMaritalStatus()!=null) updatedSubscriber.setMaritalStatus(subscriber.getMaritalStatus());
        if (subscriber.getEmail()!=null) {
            Optional<Subscriber> newSubsciber =subscriberRepository.findSubscriberByEmail(subscriber.getEmail());
            if (newSubsciber.isPresent()) throw new EmailExist(String.format("This email %s is already exist",subscriber.getEmail()));
            updatedSubscriber.setEmail(subscriber.getEmail());
        }
        updatedSubscriber.setUpdateDate(new Date());
        return subscriberRepository.save(updatedSubscriber);
    }

    @Override
    public void deleteSubscriber(String email) {
        subscriberRepository.deleteById(email);

    }
}
