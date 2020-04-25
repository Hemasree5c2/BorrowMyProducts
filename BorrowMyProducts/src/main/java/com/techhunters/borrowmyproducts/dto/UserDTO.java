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
@ToString
public class UserDTO {
	
    private String userFirstName;
    
    private String userLastName;
    
    private String userName;
    
    @Email(message = "invalid email")
    private String email;
    
    private String password;
    
    private String confirmPassword;
    
    private String phone;
    
    private AddressDTO address;
    
    
}
