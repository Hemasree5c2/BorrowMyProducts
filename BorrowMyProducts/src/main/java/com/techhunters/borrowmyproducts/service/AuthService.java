package com.techhunters.borrowmyproducts.service;


public interface AuthService {
    //to send a OTP to the user with the valid phone number given
    Boolean sendOtp(String phoneNumber);

    //to verify the user against the entered OTP.
    Boolean verifyOtp(String otp, String phoneNumber);

}
