package com.adriabt.contractservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO {
    private String subscriptionId;
    private String subscriberId ;
    private List<String> accountIds = new ArrayList<>();
    private List<String> cardIds = new ArrayList<>();
}
