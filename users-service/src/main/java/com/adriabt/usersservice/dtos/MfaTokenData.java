package com.adriabt.usersservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MfaTokenData implements Serializable {

    private String qrCode;
    private String mfaCode;
}