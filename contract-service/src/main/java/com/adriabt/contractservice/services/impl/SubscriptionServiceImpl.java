package com.adriabt.contractservice.services.impl;

import com.adriabt.contractservice.dtos.AttachmentDTO;
import com.adriabt.contractservice.dtos.OtpValidationRequest;
import com.adriabt.contractservice.entities.Subscription;
import com.adriabt.contractservice.enums.ClientSegment;
import com.adriabt.contractservice.enums.ContractStatus;
import com.adriabt.contractservice.enums.ContractType;
import com.adriabt.contractservice.exceptions.IncompleteInformation;
import com.adriabt.contractservice.exceptions.OtpInvalid;
import com.adriabt.contractservice.exceptions.SubscriptionNotFound;
import com.adriabt.contractservice.openFeignServices.SmsService;
import com.adriabt.contractservice.repositories.SubscriptionRepository;
import com.adriabt.contractservice.services.ISubscriptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements ISubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SmsService smsService;
    @Override
    public Subscription createSubscription(Subscription subscription) throws IncompleteInformation {
        if(subscription.getAgency()==null||
                subscription.getContractType()==null||
                subscription.getClientSegment()==null)throw new IncompleteInformation("Some information is messing");
        subscription.setContractStatus(ContractStatus.REGISTERED);
        subscription.setCreationDate(new Date());
        subscription.setUpdateDate(new Date());
        subscription.setCardIds(new ArrayList<>());
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription cancelOrTerminateSubscription(String subscriptionId) throws SubscriptionNotFound {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",subscriptionId)));
        if (subscription.getContractStatus()==ContractStatus.REGISTERED){
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

    @Override
    public void subscriptionAttachment(AttachmentDTO attachmentDTO) throws SubscriptionNotFound {
        Subscription subscription = subscriptionRepository.findById(attachmentDTO.getSubscriptionId())
                .orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",attachmentDTO.getSubscriptionId())));
        subscription.getSubscriberIds().add(attachmentDTO.getSubscriberId());
        attachmentDTO.getAccountIds().forEach(a->subscription.getAccountIds().add(a));
        attachmentDTO.getCardIds().forEach(a->subscription.getCardIds().add(a));
        subscriptionRepository.save(subscription);
    }

    @Override
    public void subscriptionDetachment(AttachmentDTO attachmentDTO) throws SubscriptionNotFound {
        Subscription subscription = subscriptionRepository.findById(attachmentDTO.getSubscriptionId())
                .orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",attachmentDTO.getSubscriptionId())));
        List<String> subscriberIds= subscription.getSubscriberIds().stream().filter(s->!s.equals(attachmentDTO.getSubscriberId())).collect(Collectors.toList());
        subscription.setSubscriberIds(subscriberIds);
        List<String> accountIds= subscription.getAccountIds().stream().filter(a->!a.equals(attachmentDTO.getAccountIds())).collect(Collectors.toList());
        subscription.setAccountIds(accountIds);
        subscription.getCardIds().addAll(attachmentDTO.getCardIds());
        List<String> cardIds= subscription.getCardIds().stream().filter(c->!c.equals(attachmentDTO.getCardIds())).collect(Collectors.toList());
        subscription.setCardIds(cardIds);
        subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getSubscriptionById(String subscriptionId) throws SubscriptionNotFound {
        return subscriptionRepository.findById(subscriptionId).orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",subscriptionId)));
    }

    @Override
    public Subscription signSubscription(String subscriptionId,  String otpNumber, String username,String token) throws SubscriptionNotFound, OtpInvalid {
        Subscription subscription = getSubscriptionById(subscriptionId);
        OtpValidationRequest otpValidationRequest = new OtpValidationRequest(username,otpNumber);
        boolean isOtpValid = smsService.validateOtp(otpValidationRequest,token);
        System.out.println("hey / "+smsService.validateOtp(otpValidationRequest,token));
        System.out.println(isOtpValid);
        if (isOtpValid){
            subscription.setContractStatus(ContractStatus.SIGNED);
            return subscriptionRepository.save(subscription);
        }else throw new OtpInvalid(String.format("Your otp %s is invalid",otpNumber ));
    }
}
