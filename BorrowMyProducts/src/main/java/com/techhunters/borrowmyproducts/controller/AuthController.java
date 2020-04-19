package com.techhunters.borrowmyproducts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.techhunters.borrowmyproducts.service.TwilioAuthService;

@Controller
public class AuthController
{
	@Autowired
	TwilioAuthService twilioAuthService;
	
}
