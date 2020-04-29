package com.techhunters.borrowmyproducts.controller;

import com.techhunters.borrowmyproducts.dto.AddressDTO;
import com.techhunters.borrowmyproducts.dto.CategoryDTO;
import com.techhunters.borrowmyproducts.dto.UserDTO;
import com.techhunters.borrowmyproducts.entity.Category;
import com.techhunters.borrowmyproducts.entity.User;
import com.techhunters.borrowmyproducts.entity.UserAddress;
import com.techhunters.borrowmyproducts.objectmappers.UserMapper;
import com.techhunters.borrowmyproducts.service.CategoryService;
import com.techhunters.borrowmyproducts.service.TwilioAuthService;
import com.techhunters.borrowmyproducts.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class AuthController {
    private UserDTO userDTO;
    private AddressDTO userAddressDTO;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TwilioAuthService twilioAuthService;
    @Autowired
    UserMapper userMapper;

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
        theModel.addAttribute("user", new UserDTO());
        theModel.addAttribute("address", new AddressDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String append(@ModelAttribute(name = "user") @Validated UserDTO user, @ModelAttribute(name = "address") @Validated AddressDTO address, BindingResult result, Model model) {

        userDTO = user;
        userAddressDTO = address;
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("passwordError", true);
            return "signup";
        }
//        log.info(user.getEmail());
//        log.info("user email finding in database {} ",userService.findByEmail(user.getEmail()));
        UserDTO checkDTO=userService.findByEmail(user.getEmail());
        UserDTO checkDTO1=userService.findByPhone(user.getPhone());
        if(checkDTO != null) {
            model.addAttribute("EmailExists",true);
            return "signup";
        }
        if(checkDTO1 != null) {
            model.addAttribute("phoneExists",true);
            return "signup";
        }
        if (result.hasErrors()) {
            model.addAttribute("emailError", true);
            return "signup";
        }
        log.info(user.getPhone() + user.getPhone());
        if (twilioAuthService.sendOtp(user.getPhone())) {
            log.info("otp is sent succesfully");
            return "otp";
        } else {

            return "signup";
        }
    }

    @GetMapping("/otp")
    public String redirectOtp(Model model) {
        model.addAttribute("invalidOtp", "invalid");
        return "otp";
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@ModelAttribute(name = "user") UserDTO user, @RequestParam("otp") String otp, Model model) {

        log.info("in verify otp " + userDTO.getPhone());
        if (twilioAuthService.verifyOtp(otp, userDTO.getPhone())) {
            //save the user to db and return to main page or login
            log.info("succesfully verifed the user ");

            //save here when otp is verified .
            userAddressDTO.setUser(userDTO);
            userDTO.setAddress(userAddressDTO);
            userService.save(userDTO);
            //save the user with service;
            return "redirect:/login";
        } else {
            return "redirect:/otp";
        }
    }

    @GetMapping("/mainPage")
    public String mainPage(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }

//    @PostMapping("/addCategory")
//    public String addCategory(@ModelAttribute(name = "category") CategoryDTO category, Model model) {
//        categoryService.save(category);
//        model.addAttribute("categories", categoryService.findAll());
//        return "home";
//    }
}