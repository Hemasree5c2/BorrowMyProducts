package com.techhunters.borrowmyproducts.service;


public interface AuthService 
{
	//By Default the parameters are public static final 
	 String ACCOUNT_SID = "ACa95f20b59d30fc54a834d259c85df35d";
	 String AUTH_TOKEN = "bfa5b9265825dac2e50072f273235178";
	 String  SERVICE_SID="VA31cdd65fa75c08b35a410b80c6019dc4";
    
    //to send a OTP to the user with the valid phone number given
    public Boolean  sendOTP(String phoneNumber);
    
    //to verify the user against the entered OTP.
    public Boolean verifyOTP(String OTP,String phoneNumber);
    
}
