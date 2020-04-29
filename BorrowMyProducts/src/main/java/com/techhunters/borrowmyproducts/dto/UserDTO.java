package com.techhunters.borrowmyproducts.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import com.techhunters.borrowmyproducts.entity.UserAddress;

import javax.validation.constraints.Email;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {


    private int userId;

    private String userFirstName;

    private String userLastName;

    private String password;

    private String confirmPassword;

    private String phone;

    private String email;

    private AddressDTO address;


}
