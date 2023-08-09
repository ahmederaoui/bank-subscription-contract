package com.adriabt.contractservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SignatureProfil {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int rank;
    private String description;
}
