package com.adriabt.smsservice.controllers;

import com.adriabt.smsservice.dtos.OtpRequest;
import com.adriabt.smsservice.dtos.OtpResponse;
import com.adriabt.smsservice.dtos.OtpValidationRequest;
import com.adriabt.smsservice.services.ISmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sms")
public class SmsController {
    private final ISmsService smsService;
    @PostMapping("/send")
    public ResponseEntity<?>  sendOtp(@RequestBody OtpRequest otpRequest){
        try {
            return ResponseEntity.ok(smsService.sendOtp(otpRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/validate")
    public ResponseEntity<?>  validateOtp(@RequestBody OtpValidationRequest otpValidationRequest){
        try {
            return ResponseEntity.ok(smsService.validateOtp(otpValidationRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
