package com.adriabt.usersservice.services.impl;

import com.adriabt.usersservice.dtos.VerificationRequest;
import com.adriabt.usersservice.entities.Agent;
import com.adriabt.usersservice.exceptions.AgentNotFound;
import com.adriabt.usersservice.repositories.AgentRepository;
import com.adriabt.usersservice.services.IAgentService;
import com.adriabt.usersservice.services.MFATokenManager;
import dev.samstevens.totp.code.*;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static dev.samstevens.totp.util.Utils.getDataUriForImage;

@Service
@RequiredArgsConstructor
public class DefaultMFATokenManager implements MFATokenManager {
    private final AgentRepository agentService;

    public String generateNewSecret() {
        return new DefaultSecretGenerator().generate();
    }

    public String generateQrCodeImageUri(String secret) {
        QrData data = new QrData.Builder()
                .label("Adria Business & Technology - TOTP")
                .secret(secret)
                .issuer("Adria B&T")
                .algorithm(HashingAlgorithm.SHA1)
                .digits(6)
                .period(30)
                .build();

        QrGenerator generator = new ZxingPngQrGenerator();
        byte[] imageData = new byte[0];
        try {
            imageData = generator.generate(data);
        } catch (QrGenerationException e) {
            e.printStackTrace();
        }

        return getDataUriForImage(imageData, generator.getImageMimeType());
    }

    public boolean isOtpValid(VerificationRequest verificationRequest) throws AgentNotFound {
        Agent agent = agentService.findAgentByEmail(verificationRequest.getEmail())
                .orElseThrow(()->new AgentNotFound(String.format("This agent %s Not found",verificationRequest.getEmail())));
        TimeProvider timeProvider = new SystemTimeProvider();
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        CodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
        return verifier.isValidCode(agent.getTotpSecret(), verificationRequest.getCode());
    }
}
