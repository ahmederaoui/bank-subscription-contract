package com.adriabt.accountsservice.entities;

import com.ctc.wstx.io.ISOLatinReader;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String accountNumber;
    private String accountTitle;
    private Boolean convertible;
    private Double balance;
    private List<String> attachmentIds;
    private String contractId;
}
