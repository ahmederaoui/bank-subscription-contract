package com.adriabt.contractservice.services.impl;

import com.adriabt.contractservice.entities.Subscription;
import com.adriabt.contractservice.enums.ClientSegment;
import com.adriabt.contractservice.enums.ContractStatus;
import com.adriabt.contractservice.enums.ContractType;
import com.adriabt.contractservice.exceptions.IncompleteInformation;
import com.adriabt.contractservice.exceptions.SubscriptionNotFound;
import com.adriabt.contractservice.repositories.SubscriptionRepository;
import com.adriabt.contractservice.services.ISubscriptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements ISubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    @Override
    public Subscription createSubscription(Subscription subscription) throws IncompleteInformation {
        if(subscription.getAgency()==null||
                subscription.getContractType()==null||
                subscription.getClientSegment()==null)throw new IncompleteInformation("Some information is messing");
        subscription.setContractStatus(ContractStatus.REGISTRED);
        subscription.setCreationDate(new Date());
        subscription.setUpdateDate(new Date());
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription cancelOrTerminateSubscription(String subscriptionId) throws SubscriptionNotFound {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",subscriptionId)));
        if (subscription.getContractStatus()==ContractStatus.REGISTRED){
            subscription.setContractStatus(ContractStatus.CANCELED);
        } else if (subscription.getContractStatus()==ContractStatus.SIGNED) {
            subscription.setContractStatus(ContractStatus.TERMINATED);
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription updateSubscription(Subscription subscription) throws SubscriptionNotFound {
        Subscription newSubscription = subscriptionRepository.findById(subscription.getId())
                .orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",subscription.getId())));
        if (subscription.getAgency()!=null)newSubscription.setAgency(subscription.getAgency());
        if (subscription.getBankCode()!=null)newSubscription.setBankCode(subscription.getBankCode());
        if (subscription.getAddress()!=null)newSubscription.setAddress(subscription.getAddress());
        if (subscription.getClientSegment()!=null)newSubscription.setClientSegment(subscription.getClientSegment());
        newSubscription.setUpdateDate(new Date());
        return subscriptionRepository.save(newSubscription);
    }

    @Override
    public Page<Subscription> getSubscriptions(String agency, ContractStatus contractStatus, ContractType contractType,ClientSegment clientSegment,String id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return subscriptionRepository.findAllByAgencyContainsIgnoreCaseAndContractStatusAndContractTypeAndClientSegmentAndIdStartingWith(agency,contractStatus,contractType,clientSegment, id,pageable);
    }
}
