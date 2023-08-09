package com.adriabt.contractservice.services;

import com.adriabt.contractservice.entities.SignatureMatrix;
import com.adriabt.contractservice.entities.SignatureProfil;
import com.adriabt.contractservice.entities.Subscription;
import org.springframework.data.domain.Page;

public interface ISubscriptionService {
    Subscription createSubscription(Subscription subscription);
    Subscription addSignatureProfilToSubscription(String signatureProfileId);
    Subscription addSignatureMatrixToSubscription(String signatureMatrixId);
    Subscription cancelOrTerminateSubscription();
    Subscription updateSubscription(Subscription subscription);
    Subscription deleteSignatureProfileFromSubscription(String signatureProfileId);
    Subscription deleteSignatureMatrixFromSubscription(String signatureMatrixId);
    Page<Subscription> getSubscription(String Keyword,int page,int size);

}
