package com.techhunters.borrowmyproducts.controller;
import com.techhunters.borrowmyproducts.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
    public String append(@ModelAttribute(name="user") UserDTO user,Model model) {
        String regexEmail="^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regexEmail);
        if(!pattern.matcher(user.getEmail()).matches()) {
            model.addAttribute("emailError",true);
            return "signup";
        }
        if(!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("passwordError",true);
            return "signup";
        }
        String regexPhone="[0-9]+";
        Pattern pat=Pattern.compile(regexPhone);
        if(!pat.matcher(user.getPhoneNo()).matches()) {
            model.addAttribute("numberError",true);
            return "signup";
        }
        if(!pat.matcher(user.getPincode()).matches()) {
            model.addAttribute("numberError",true);
            return "signup";
        }
        objects.add(user);
        return "login";
    }
}
