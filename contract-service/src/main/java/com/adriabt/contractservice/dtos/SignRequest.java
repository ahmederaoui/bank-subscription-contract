package com.adriabt.contractservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignRequest {
    private String subscriptionId;
    private String username;
    private String otpNumber;
}
