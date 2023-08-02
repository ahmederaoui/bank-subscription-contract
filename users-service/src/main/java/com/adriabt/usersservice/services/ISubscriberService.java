package com.adriabt.usersservice.services;

import com.adriabt.usersservice.dtos.SearchCaractures;
import com.adriabt.usersservice.entities.Subscriber;
import com.adriabt.usersservice.enums.MaritalStatus;
import com.adriabt.usersservice.exceptions.EmailExist;
import com.adriabt.usersservice.exceptions.MissingInformation;
import com.adriabt.usersservice.exceptions.SubscriberNotFound;
import org.springframework.data.domain.Page;

public interface ISubscriberService {
    Subscriber createSubscriber(Subscriber subscriber) throws EmailExist, MissingInformation;
    Page<Subscriber> findAllSubscribersByKeyword(int page, int size);
    Subscriber findSubscriberByEmail(String email) throws SubscriberNotFound;
    Subscriber updateSubscriber(Subscriber subscriber) throws SubscriberNotFound, EmailExist;
    boolean deleteSubscriber(String email);
}
