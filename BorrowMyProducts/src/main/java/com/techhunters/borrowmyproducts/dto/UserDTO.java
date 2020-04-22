package com.techhunters.borrowmyproducts.dto;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String lastName;
    @Email(message = "invalid email")
    private String email;
    private String password;
    private String confirmPassword;
    private String phoneNo;
    private String state;
    private String country;
    private String city;
    private String doorNo;
    private String pincode;
    private String latitude;
    private String longitude;
    private int otp;
}
