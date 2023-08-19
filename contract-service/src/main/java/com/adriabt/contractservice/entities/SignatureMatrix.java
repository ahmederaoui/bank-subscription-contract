package com.adriabt.contractservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class SignatureMatrix {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String authorizedOperation;
    private Double minAmount;
    private Double maxAmount;
    @OneToMany
    private List<SignatureProfile> signatureProfiles = new ArrayList<>();
}
