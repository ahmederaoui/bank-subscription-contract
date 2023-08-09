package com.adriabt.contractservice.repositories;

import com.adriabt.contractservice.entities.MobileCeiling;
import com.adriabt.contractservice.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileCeilingRepository extends JpaRepository<MobileCeiling,String> {
}
