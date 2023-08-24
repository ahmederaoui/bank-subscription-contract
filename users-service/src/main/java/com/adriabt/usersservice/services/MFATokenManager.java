package com.adriabt.usersservice.services;

import com.adriabt.usersservice.dtos.VerificationRequest;
import com.adriabt.usersservice.exceptions.AgentNotFound;
import dev.samstevens.totp.exceptions.QrGenerationException;

public interface MFATokenManager {
    String generateNewSecret();
    String generateQrCodeImageUri(String secret) ;
    boolean isOtpValid(VerificationRequest verificationRequest)throws AgentNotFound;
}