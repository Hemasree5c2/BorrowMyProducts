package com.techhunters.borrowmyproducts.controller;
import com.techhunters.borrowmyproducts.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController {
    List<UserDTO> objects=new ArrayList<UserDTO>();
    @GetMapping("/")
    public String login() {
        return "login";
    }
    @GetMapping("/signup")
    public String register() {
        return "signup";
    }
    @PostMapping("/")
    public String validate(@ModelAttribute(name="user") UserDTO user, Model model) {
        if(user.getLatitude()=="") {
            model.addAttribute("locationError",true);
            return "login";
        }
        if(objects.size()!=0) {
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).getEmail().equals(user.getEmail()) && objects.get(i).getPassword().equals(user.getPassword())) {
                    model.addAttribute("user", objects.get(i));
                    return "home";
                }
            }
        }
        model.addAttribute("InvalidCredentials",true);
        return "login";
    }
    @PostMapping("/signup")
    public String append(@ModelAttribute(name="user") @Validated UserDTO user, BindingResult result, Model model) {
        if(!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("passwordError",true);
            return "signup";
        }
        if(result.hasErrors()) {
            model.addAttribute("emailError",true);
            return "signup";
        }
        objects.add(user);
        return "otp";
    }
    @PostMapping("/otp")
    public String verifyOtp(@ModelAttribute(name="user") UserDTO user,Model model) {
        if(user.getOtp()==1234) {
            return "login";
        }
        else {
            return "otp";
        }
    }
}