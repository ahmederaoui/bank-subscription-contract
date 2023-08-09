package com.adriabt.contractservice.repositories;

import com.adriabt.contractservice.entities.SignatureProfil;
import com.adriabt.contractservice.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureProfilRepository extends JpaRepository<SignatureProfil,String> {
}
