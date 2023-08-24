package com.adriabt.attachmentsservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private String id;
    private WebCeiling webCeiling;
    private MobileCeiling mobileCeiling;
    private List<SignatureProfile> signatureProfiles;
}
