package com.techhunters.borrowmyproducts.service;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

public class TwilioAuthService implements AuthService
{

	@Override
	public Boolean sendOTP(String phoneNumber) 
	{
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
       try
       {
		Verification verification = Verification.creator(SERVICE_SID, phoneNumber,"sms")
            .create();
        
        //just printing on the console for verification
        //System.out.println(verification.getStatus().toUpperCase());
        
        return true;
       }
      
       catch(Exception exception)
       {
    	  
    	   return false;
       }
		
	}

	@Override
	public Boolean verifyOTP(String OTP,String phoneNumber)
	{
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		VerificationCheck verificationCheck = VerificationCheck
								.creator(SERVICE_SID,OTP)
								.setTo(phoneNumber).create();

        if(verificationCheck.getStatus()== "approved")
        {
        	return true;
		}
        else
        {
        	return false;
        }
	
	}

	
}
