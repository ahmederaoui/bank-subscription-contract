package com.adriabt.contractservice.entities;

import com.adriabt.contractservice.enums.ClientSegment;
import com.adriabt.contractservice.enums.ContractStatus;
import com.adriabt.contractservice.enums.ContractType;
import jakarta.persistence.*;
import jakarta.ws.rs.GET;
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
    private Long contractNum;
    private ContractType contractType;
    private String agency;
    private Long BankCode;
    private ClientSegment clientSegment;
    private String address;
    private Date creationDate;
    private Date signatureDate;
    private Date updateDate;
    private ContractStatus contractStatus;
    private String agentId;
    @OneToMany
    private List<SignatureMatrix> signatureMatrices = new ArrayList<>();
    @OneToMany
    private List<SignatureProfil> signatureProfils = new ArrayList<>();
    @OneToOne
    private WebCeiling webCeiling;
    @OneToOne
    private MobileCeiling mobileCeiling;
    private List<String> subscriberIds = new ArrayList<>();
    private List<String> accountIds = new ArrayList<>();
    private List<String> carteIds = new ArrayList<>();
}
