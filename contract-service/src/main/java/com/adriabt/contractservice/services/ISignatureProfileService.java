package com.adriabt.contractservice.services;

import com.adriabt.contractservice.entities.SignatureProfile;
import com.adriabt.contractservice.exceptions.IncompleteInformation;
import com.adriabt.contractservice.exceptions.SignatureProfileNotFound;
import com.adriabt.contractservice.exceptions.SubscriptionNotFound;

import java.util.List;

public interface ISignatureProfileService {
    SignatureProfile createSignatureProfileForSubscription(SignatureProfile signatureProfile, String subscriptionId) throws SubscriptionNotFound, IncompleteInformation;
    SignatureProfile updateSignatureProfileForSubscription(SignatureProfile signatureProfile) throws SignatureProfileNotFound;
    List<SignatureProfile> findAllSignatureProfileForSubscription(String subscriptionId) throws SubscriptionNotFound;
    void deleteSignatureProfileForSubscription(String signatureProfileId);
}
