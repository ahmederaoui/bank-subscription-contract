package com.adriabt.contractservice.services.impl;

import com.adriabt.contractservice.entities.SignatureProfile;
import com.adriabt.contractservice.entities.Subscription;
import com.adriabt.contractservice.exceptions.IncompleteInformation;
import com.adriabt.contractservice.exceptions.SignatureProfileNotFound;
import com.adriabt.contractservice.exceptions.SubscriptionNotFound;
import com.adriabt.contractservice.repositories.SignatureProfilRepository;
import com.adriabt.contractservice.repositories.SubscriptionRepository;
import com.adriabt.contractservice.services.ISignatureProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SignatureProfileService implements ISignatureProfileService {
    private final SignatureProfilRepository profilRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SignatureProfile createSignatureProfileForSubscription(SignatureProfile signatureProfile, String subscriptionId) throws SubscriptionNotFound, IncompleteInformation {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",subscriptionId)));
        if (signatureProfile.getRank()==null || signatureProfile.getDescription()==null) throw new IncompleteInformation("Some information is messing");
        SignatureProfile newSignatureProfile = profilRepository.save(signatureProfile);
        subscription.getSignatureProfiles().add(newSignatureProfile);
        subscriptionRepository.save(subscription);
        return newSignatureProfile;
    }

    @Override
    public SignatureProfile updateSignatureProfileForSubscription(SignatureProfile signatureProfile) throws SignatureProfileNotFound {
        SignatureProfile updatedSignatureProfile = profilRepository.findById(signatureProfile.getId())
                .orElseThrow(()->new SignatureProfileNotFound(String.format("This signature profile %s not found",signatureProfile.getId())));
        if(signatureProfile.getRank()!=null)updatedSignatureProfile.setRank(signatureProfile.getRank());
        if(signatureProfile.getDescription()!=null)updatedSignatureProfile.setDescription(signatureProfile.getDescription());
        return profilRepository.save(updatedSignatureProfile);
    }

    @Override
    public List<SignatureProfile> findAllSignatureProfileForSubscription(String subscriptionId) throws SubscriptionNotFound {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",subscriptionId)));
        return subscription.getSignatureProfiles();
    }

    @Override
    public void deleteSignatureProfileForSubscription(String signatureProfileId) {
        profilRepository.deleteById(signatureProfileId);
    }
}
