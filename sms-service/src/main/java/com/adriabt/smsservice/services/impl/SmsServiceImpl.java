package com.adriabt.smsservice.services.impl;

import com.adriabt.smsservice.config.InfobipConfig;
import com.adriabt.smsservice.dtos.OtpRequest;
import com.adriabt.smsservice.dtos.OtpResponse;
import com.adriabt.smsservice.dtos.OtpStatus;
import com.adriabt.smsservice.dtos.OtpValidationRequest;
import com.adriabt.smsservice.services.ISmsService;
import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.SmsApi;
import com.infobip.model.SmsAdvancedTextualRequest;
import com.infobip.model.SmsDestination;
import com.infobip.model.SmsTextualMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements ISmsService {
    private  final InfobipConfig infobipConfig;
    Map<String,String> otpMap = new HashMap<>();
    @Override
    public OtpResponse sendOtp(OtpRequest otpRequest) {
        String otp = generateOtp();
        System.out.println(otp);
        var apiClient = ApiClient.forApiKey(ApiKey.from(infobipConfig.getKey()))
                .withBaseUrl(BaseUrl.from(infobipConfig.getUrl()))
                .build();
        var smsApi = new SmsApi(apiClient);
        //String otp = generateOtp();
        String recipient = "212"+otpRequest.getPhoneNumber().substring(1);
        String otpMessage = "Dear Customer , Your OTP from AdriaBt is  " + otp + " . Thank You.";
        var smsMessage = new SmsTextualMessage()
                .from("AdraiaBt")
                .addDestinationsItem(new SmsDestination().to(recipient))
                .text(otpMessage);

        var smsMessageRequest = new SmsAdvancedTextualRequest()
                .messages(Collections.singletonList(smsMessage));
        OtpResponse otpResponse = null;
        try {
            var smsResponse = smsApi.sendSmsMessage(smsMessageRequest).execute();
            var reportsResponse = smsApi.getOutboundSmsMessageDeliveryReports().execute();
            otpMap.put(otpRequest.getUsername(), otp);
            otpResponse = new OtpResponse(OtpStatus.DELIVERED, otpMessage);

        } catch (ApiException e) {
            e.printStackTrace();
            otpResponse = new OtpResponse(OtpStatus.FAILED, e.getMessage());
        }
        return otpResponse;
    }

    @Override
    public boolean validateOtp(OtpValidationRequest otpValidationRequest) {
        Set<String> keys = otpMap.keySet();
        String username = null;
        for(String key : keys)
            username = key;
        if (otpValidationRequest.getUsername().equals(username) && otpMap.get(username).equals(otpValidationRequest.getOtpNumber())) {
            otpMap.remove(username,otpValidationRequest.getOtpNumber());
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String generateOtp() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
}
