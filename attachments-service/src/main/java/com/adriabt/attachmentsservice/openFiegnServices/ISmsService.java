package com.adriabt.attachmentsservice.openFiegnServices;

import com.adriabt.attachmentsservice.models.OtpValidationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SMS-SERVICE")
public interface ISmsService {
    @PostMapping("/api/sms/validate")
    boolean validateOtp(@RequestBody OtpValidationRequest otpValidationRequest);
}
