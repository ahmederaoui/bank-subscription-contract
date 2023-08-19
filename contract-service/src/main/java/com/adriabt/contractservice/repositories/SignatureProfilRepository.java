package com.adriabt.contractservice.repositories;

import com.adriabt.contractservice.entities.SignatureProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureProfilRepository extends JpaRepository<SignatureProfile,String> {
}
