package com.adriabt.contractservice.repositories;

import com.adriabt.contractservice.entities.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,String> {
    Page<Subscription> findAllByAgencyContainsIgnoreCaseAndContractStatusIsContainingIgnoreCaseAndContractTypeIsContainingIgnoreCaseAndCAndContractNumStartingWith(String agency, String contractStatus, String contractType, Long contractNum, Pageable pageable);
}
