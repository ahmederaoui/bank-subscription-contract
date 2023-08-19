package com.adriabt.contractservice.repositories;

import com.adriabt.contractservice.entities.Subscription;
import com.adriabt.contractservice.enums.ClientSegment;
import com.adriabt.contractservice.enums.ContractStatus;
import com.adriabt.contractservice.enums.ContractType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,String> {
    Page<Subscription> findAllByAgencyContainsIgnoreCaseAndContractStatusAndContractTypeAndClientSegmentAndIdStartingWith(String agency, ContractStatus contractStatus, ContractType contractType, ClientSegment clientSegment,String id ,Pageable pageable);
}
