package com.techhunters.borrowmyproducts.controller;
import com.techhunters.borrowmyproducts.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @PostMapping("/validate")
    public String validate(@ModelAttribute(name="user") UserDTO user, Model model) {
        if(objects.size()!=0) {
            System.out.println(objects.size());
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
    @PostMapping("/append")
    public String append(@ModelAttribute(name="user") UserDTO user,Model model) {
        objects.add(user);
        return "login";
    }
}
