package com.adriabt.contractservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Ceiling {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double minUnitAmount;
    private double maxUnitCeiling;
    private double TransferPerDay;
}
