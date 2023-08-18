package com.adriabt.contractservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class SignatureProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer rank;
    private String description;
}
