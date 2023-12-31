package com.adriabt.contractservice.entities;

import com.adriabt.contractservice.enums.ClientSegment;
import com.adriabt.contractservice.enums.ContractStatus;
import com.adriabt.contractservice.enums.ContractType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractNum;
    @Enumerated(value = EnumType.STRING)
    private ContractType contractType;
    private String agency;
    private Long bankCode;
    @Enumerated(value = EnumType.STRING)
    private ClientSegment clientSegment;
    private String address;
    private Date creationDate;
    private Date signatureDate;
    private Date updateDate;
    @Enumerated(value = EnumType.STRING)
    private ContractStatus contractStatus;
    private String agentId;
    @OneToMany
    private List<SignatureMatrix> signatureMatrices = new ArrayList<>();
    @OneToMany
    private List<SignatureProfile> signatureProfiles = new ArrayList<>();
    @OneToOne
    private WebCeiling webCeiling;
    @OneToOne
    private MobileCeiling mobileCeiling;
    private List<String> subscriberIds = new ArrayList<>();
    private List<String> accountIds = new ArrayList<>();
    private List<String> cardIds = new ArrayList<>();
}
