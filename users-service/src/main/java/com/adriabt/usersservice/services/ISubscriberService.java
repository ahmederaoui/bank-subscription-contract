package com.adriabt.usersservice.services;

import com.adriabt.usersservice.entities.Subscriber;
import com.adriabt.usersservice.exceptions.EmailExist;
import com.adriabt.usersservice.exceptions.MissingInformation;
import com.adriabt.usersservice.exceptions.SubscriberNotFound;
import org.springframework.data.domain.Page;

public interface ISubscriberService {
    Subscriber createSubscriber(Subscriber subscriber) throws EmailExist, MissingInformation;
    Page<Subscriber> findAllSubscribersByKeyword(String keyword,int page, int size);
    Subscriber findSubscriberByEmail(String email) throws SubscriberNotFound;
    Subscriber updateSubscriber(Subscriber subscriber) throws SubscriberNotFound, EmailExist;
    void deleteSubscriber(String email);
}
