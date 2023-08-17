package com.adriabt.contractservice.services;

import com.adriabt.contractservice.entities.SignatureMatrix;
import com.adriabt.contractservice.exceptions.IncompleteInformation;
import com.adriabt.contractservice.exceptions.SignatureMatrixNotFound;
import com.adriabt.contractservice.exceptions.SubscriptionNotFound;

import java.util.List;

public interface ISignatureMatrixService {
    SignatureMatrix createSignatureMatrixForSubscription(SignatureMatrix signatureMatrix, String subscriptionId) throws SubscriptionNotFound, IncompleteInformation;
    SignatureMatrix updateSignatureMatrixForSubscription(SignatureMatrix signatureMatrix) throws SignatureMatrixNotFound;
    List<SignatureMatrix> findAllSignatureMatrixForSubscription(String subscriptionId) throws SubscriptionNotFound;
    void deleteSignatureMatrixForSubscription(String signatureMatrixID);




}
