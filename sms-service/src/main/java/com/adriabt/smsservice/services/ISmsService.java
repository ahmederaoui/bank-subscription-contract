package com.adriabt.smsservice.services;

import com.adriabt.smsservice.dtos.OtpRequest;
import com.adriabt.smsservice.dtos.OtpResponse;
import com.adriabt.smsservice.dtos.OtpValidationRequest;

public interface ISmsService {
    OtpResponse sendOtp(OtpRequest otpRequest);
    String validateOtp(OtpValidationRequest otpValidationRequest);
    String generateOtp();
}
