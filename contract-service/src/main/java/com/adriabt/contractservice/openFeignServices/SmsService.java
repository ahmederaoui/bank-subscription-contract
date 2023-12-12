package com.adriabt.contractservice.openFeignServices;

import com.adriabt.contractservice.dtos.OtpValidationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "SMS-SERVICE")
public interface SmsService {
    @PostMapping("/api/sms/validate")
    boolean validateOtp(@RequestBody OtpValidationRequest otpValidationRequest, @RequestHeader("Authorization")String token);
}
