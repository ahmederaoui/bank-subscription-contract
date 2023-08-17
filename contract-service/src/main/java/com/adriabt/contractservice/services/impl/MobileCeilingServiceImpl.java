package com.adriabt.contractservice.services.impl;

import com.adriabt.contractservice.entities.MobileCeiling;
import com.adriabt.contractservice.entities.Subscription;
import com.adriabt.contractservice.exceptions.CeilingNotFound;
import com.adriabt.contractservice.exceptions.IncompleteInformation;
import com.adriabt.contractservice.exceptions.SubscriptionNotFound;
import com.adriabt.contractservice.repositories.MobileCeilingRepository;
import com.adriabt.contractservice.repositories.SubscriptionRepository;
import com.adriabt.contractservice.services.ICeilingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MobileCeilingServiceImpl implements ICeilingService<MobileCeiling> {
    private final MobileCeilingRepository ceilingRepository;
    private final SubscriptionRepository subscriptionRepository;
    @Override
    public MobileCeiling createCeiling(MobileCeiling ceiling, String subscriptionId) throws SubscriptionNotFound, IncompleteInformation {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",subscriptionId)));
        if (ceiling.getMaxUnitCeiling().isNaN() || ceiling.getMinUnitAmount().isNaN() || ceiling.getTransferPerDay().isNaN()) throw new IncompleteInformation("Some information is messing");
        MobileCeiling newCeiling = ceilingRepository.save(ceiling);
        subscription.setMobileCeiling(newCeiling);
        return newCeiling;
    }

    @Override
    public MobileCeiling updateCeiling(MobileCeiling ceiling) throws CeilingNotFound {
        MobileCeiling newCeiling = ceilingRepository.findById(ceiling.getId()).orElseThrow(()->new CeilingNotFound("the updated ceiling not found"));
        if (!ceiling.getMaxUnitCeiling().isNaN()) newCeiling.setMaxUnitCeiling(ceiling.getMaxUnitCeiling());
        if (!ceiling.getMinUnitAmount().isNaN()) newCeiling.setMinUnitAmount(ceiling.getMinUnitAmount());
        if (!ceiling.getTransferPerDay().isNaN()) newCeiling.setTransferPerDay(ceiling.getTransferPerDay());
        return ceilingRepository.save(newCeiling);
    }

    @Override
    public MobileCeiling FindCeiling(String subscriptionId) throws SubscriptionNotFound {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()->new SubscriptionNotFound(String.format("This subscription %s not found",subscriptionId)));
        return subscription.getMobileCeiling();
    }

    @Override
    public void deleteCeiling(String ceilingId) {
        ceilingRepository.deleteById(ceilingId);
    }
}
