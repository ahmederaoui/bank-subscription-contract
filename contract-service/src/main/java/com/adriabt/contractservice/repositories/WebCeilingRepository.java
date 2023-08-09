package com.adriabt.contractservice.repositories;

import com.adriabt.contractservice.entities.Subscription;
import com.adriabt.contractservice.entities.WebCeiling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebCeilingRepository extends JpaRepository<WebCeiling,String> {
}
