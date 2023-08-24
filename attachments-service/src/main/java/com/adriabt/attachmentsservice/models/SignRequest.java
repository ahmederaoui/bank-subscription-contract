package com.adriabt.attachmentsservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignRequest {
    private String attachmentId;
    private String otpNumber;
}
