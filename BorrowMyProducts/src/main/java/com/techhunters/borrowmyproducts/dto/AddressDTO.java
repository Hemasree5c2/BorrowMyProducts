package com.techhunters.borrowmyproducts.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.techhunters.borrowmyproducts.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private int userAddressId;

    private UserDTO user;

    private String houseNo;

    private String StreetNo;

    private String city;

    private String state;

    private String country;

    private String zipCode;
}
