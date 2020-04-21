package com.techhunters.borrowmyproducts.controller;

import com.techhunters.borrowmyproducts.service.TwilioAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    private TwilioAuthService twilioAuthService;

    @Autowired
    public AuthController(TwilioAuthService twilioAuthService) {
        this.twilioAuthService = twilioAuthService;
    }

}