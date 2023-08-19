package com.adriabt.contractservice.services;
import com.adriabt.contractservice.exceptions.CeilingNotFound;
import com.adriabt.contractservice.exceptions.IncompleteInformation;
import com.adriabt.contractservice.exceptions.SubscriptionNotFound;

public interface ICeilingService<T> {
    T createCeiling(T ceiling,String subscriptionId) throws SubscriptionNotFound, IncompleteInformation;
    T updateCeiling(T ceiling) throws CeilingNotFound;
    T FindCeiling(String subscriptionId) throws SubscriptionNotFound;
    void deleteCeiling(String ceilingId,String subscriptionId) throws SubscriptionNotFound;

}
