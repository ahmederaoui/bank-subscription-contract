package com.adriabt.usersservice.repositories;

import com.adriabt.usersservice.entities.AppUser;
import com.adriabt.usersservice.entities.Subscriber;
import com.adriabt.usersservice.enums.MaritalStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber,String> {
    Page<Subscriber> findAll(Pageable pageable);
    Optional<Subscriber> findSubscriberByEmail(String email);
    boolean  deleteSubscriberByEmail(String email);
    //Page<Subscription> findAllByAgencyContainsIgnoreCaseAndContractStatusAndContractTypeAndClientSegmentAndIdStartingWith(String agency, ContractStatus contractStatus, ContractType contractType, ClientSegment clientSegment,String id ,Pageable pageable);
    Page<Subscriber> findAllByFirstnameContainsIgnoreCaseAndLastnameContainsIgnoreCaseAndEmailContainsIgnoreCaseAndNationalityContainsIgnoreCaseAndMaritalStatusAndCinStartingWithAndIdStartingWith(String firstname,String lastname, String email,String nationality, MaritalStatus maritalStatus,String cin, String id,Pageable pageable);
}
