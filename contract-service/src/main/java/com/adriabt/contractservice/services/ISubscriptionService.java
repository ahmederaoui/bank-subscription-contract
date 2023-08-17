package com.adriabt.contractservice.services;

import com.adriabt.contractservice.entities.Subscription;
import com.adriabt.contractservice.exceptions.IncompleteInformation;
import com.adriabt.contractservice.exceptions.SubscriptionNotFound;
import org.springframework.data.domain.Page;

public interface ISubscriptionService {
    Subscription createSubscription(Subscription subscription) throws IncompleteInformation;
    Subscription cancelOrTerminateSubscription(String subscriptionId) throws SubscriptionNotFound;
    Subscription updateSubscription(Subscription subscription) throws SubscriptionNotFound;
    Page<Subscription> getSubscriptions(String agency, String contractStatus, String contractType, Long contractNum,int page,int size);

}
