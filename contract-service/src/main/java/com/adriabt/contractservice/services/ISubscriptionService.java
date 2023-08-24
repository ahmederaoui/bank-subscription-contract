package com.adriabt.contractservice.services;

import com.adriabt.contractservice.dtos.AttachmentDTO;
import com.adriabt.contractservice.entities.Subscription;
import com.adriabt.contractservice.enums.ClientSegment;
import com.adriabt.contractservice.enums.ContractStatus;
import com.adriabt.contractservice.enums.ContractType;
import com.adriabt.contractservice.exceptions.IncompleteInformation;
import com.adriabt.contractservice.exceptions.SubscriptionNotFound;
import org.springframework.data.domain.Page;

public interface ISubscriptionService {
    Subscription createSubscription(Subscription subscription) throws IncompleteInformation;
    Subscription cancelOrTerminateSubscription(String subscriptionId) throws SubscriptionNotFound;
    Subscription updateSubscription(Subscription subscription) throws SubscriptionNotFound;
    Page<Subscription> getSubscriptions(String agency, ContractStatus contractStatus, ContractType contractType,  ClientSegment clientSegment, String id,int page, int size);
    void subscriptionAttachment(AttachmentDTO attachmentDTO) throws SubscriptionNotFound;
    void subscriptionDetachment(AttachmentDTO attachmentDTO) throws SubscriptionNotFound;
    Subscription getSubscriptionById(String subscriptionId) throws SubscriptionNotFound;

}
