package com.techhunters.borrowmyproducts.controller;
import com.techhunters.borrowmyproducts.dto.AddressDTO;
import com.techhunters.borrowmyproducts.dto.UserDTO;
import com.techhunters.borrowmyproducts.entity.User;
import com.techhunters.borrowmyproducts.entity.UserAddress;
import com.techhunters.borrowmyproducts.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class AuthController {
	
    List<UserDTO> objects=new ArrayList<UserDTO>();
    
    @Autowired
    UserService userService;
    
    @GetMapping("/")
    public String showLogin() {
    	return "redirect:/login";
    }
    
    
    @GetMapping("/login")
    public String login() {
 
        return "login";
    }
    
    @GetMapping("/signup")
    public String register(Model theModel) {
    	
    	UserDTO userDTO=new UserDTO();
    	AddressDTO addressDTO=new AddressDTO();
    	theModel.addAttribute("user",userDTO);
    	theModel.addAttribute("address",addressDTO);
//    	User user=new User();
//    	UserAddress address=new UserAddress();
//    	theModel.addAttribute("user",user);
//        theModel.addAttribute("address",address);
        
        return "signup";
    }
    

    
    @PostMapping("/signup")
    public String append(@ModelAttribute(name="user") @Validated UserDTO user,@ModelAttribute(name="address") @Validated AddressDTO address, BindingResult result, Model model) {
   /*     if(!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("passwordError",true);
            return "signup";
        }*/
        if(result.hasErrors()) {
            model.addAttribute("emailError",true);
            return "signup";
        }
        address.setUser(user);
        user.setAddress(address);
        userService.save(user);
        return "otp";
    }
    
    @PostMapping("/otp")
    public String verifyOtp(@ModelAttribute(name="user") UserDTO user,Model model) {
            return "login";
        
        
    }
    
    @GetMapping("/mainPage")
    public String mainPage() {
    	return "mainpage";
    }
}