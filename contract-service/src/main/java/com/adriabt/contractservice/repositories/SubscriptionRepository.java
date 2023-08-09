package com.adriabt.contractservice.repositories;

import com.adriabt.contractservice.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,String> {
}
