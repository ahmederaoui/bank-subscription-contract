package com.adriabt.contractservice.services.impl;

import com.adriabt.contractservice.entities.SignatureMatrix;
import com.adriabt.contractservice.entities.Subscription;
import com.adriabt.contractservice.exceptions.IncompleteInformation;
import com.adriabt.contractservice.exceptions.SignatureMatrixNotFound;
import com.adriabt.contractservice.exceptions.SubscriptionNotFound;
import com.adriabt.contractservice.repositories.SignatureMatrixRepository;
import com.adriabt.contractservice.repositories.SubscriptionRepository;
import com.adriabt.contractservice.services.ISignatureMatrixService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SignatureMatrixServiceImpl implements ISignatureMatrixService {
    private final SignatureMatrixRepository matrixRepository;
    private final SubscriptionRepository subscriptionRepository;


    @Override
    public SignatureMatrix createSignatureMatrixForSubscription(SignatureMatrix signatureMatrix, String subscriptionId) throws SubscriptionNotFound, IncompleteInformation {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",subscriptionId)));
        if (signatureMatrix.getMaxAmount().isNaN()||
                signatureMatrix.getMinAmount().isNaN()||
                signatureMatrix.getAuthorizedOperation()==null) throw new IncompleteInformation("Some information is messing");
        System.out.println(signatureMatrix);
        SignatureMatrix newSignature = matrixRepository.save(signatureMatrix);
        subscription.getSignatureMatrices().add(newSignature);
        System.out.println(subscription);
        return newSignature;
    }

    @Override
    public SignatureMatrix updateSignatureMatrixForSubscription(SignatureMatrix signatureMatrix) throws SignatureMatrixNotFound {
        SignatureMatrix newSignature = matrixRepository.findById(signatureMatrix.getId()).orElseThrow(()->new SignatureMatrixNotFound(String.format("This signature matrix %s not found",signatureMatrix.getId())));
        if(!signatureMatrix.getMinAmount().isNaN()) newSignature.setMinAmount(signatureMatrix.getMinAmount());
        if(!signatureMatrix.getMaxAmount().isNaN()) newSignature.setMaxAmount(signatureMatrix.getMaxAmount());
        if(signatureMatrix.getAuthorizedOperation()!=null) newSignature.setAuthorizedOperation(signatureMatrix.getAuthorizedOperation());
        if(signatureMatrix.getSignatureProfiles()!=null) newSignature.setSignatureProfiles(signatureMatrix.getSignatureProfiles());
        return matrixRepository.save(newSignature);
    }

    @Override
    public List<SignatureMatrix> findAllSignatureMatrixForSubscription(String subscriptionId) throws SubscriptionNotFound {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",subscriptionId)));
        return subscription.getSignatureMatrices();
    }

    @Override
    public void deleteSignatureMatrixForSubscription(String signatureMatrixId,String subscriptionId) throws SubscriptionNotFound {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",subscriptionId)));
        List<SignatureMatrix> signatureMatrices = subscription.getSignatureMatrices().stream().filter(s->!s.getId().equals(signatureMatrixId)).collect(Collectors.toList());
        subscription.setSignatureMatrices(signatureMatrices);
        subscriptionRepository.save(subscription);
        matrixRepository.deleteById(signatureMatrixId);
    }
}
