package com.techhunters.borrowmyproducts.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import com.techhunters.borrowmyproducts.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TwilioAuthService implements AuthService {

    @Autowired
    TwilioConfig twilioConfig;

    @Override
    public Boolean sendOtp(String phoneNumber) {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
        try {
            Verification verification = Verification
                    .creator(twilioConfig.getServiceSid(), phoneNumber, "sms")
                    .create();


           log.info(verification.getStatus());

            return true;
        } catch (Exception exception) {

            log.info("user provided the invalid phone number {}",phoneNumber);
            return false;
        }

    }

    @Override
    public Boolean verifyOtp(String otp, String phoneNumber) {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
        VerificationCheck verificationCheck = VerificationCheck
                .creator(twilioConfig.getServiceSid(), otp)
                .setTo(phoneNumber).create();

        if (verificationCheck.getStatus().equals("approved")) {
            log.info("successfully verified otp");
            return true;
        } else {
            log.info("user entered a wrong otp {} for this phone number {}",otp,phoneNumber);
            return false;
        }

    }


}
