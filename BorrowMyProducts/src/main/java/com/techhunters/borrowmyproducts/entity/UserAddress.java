package com.techhunters.borrowmyproducts.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Address")
public class UserAddress {
   
	@OneToOne
    @JoinColumn(name = "User_id", referencedColumnName = "User_id")
    private User user;
	
	@Column(name="House_No")
	private String houseNo;
	
	@Column(name="Street_No")
	private String StreetNo;
	
	@Column(name="City")
	private String city;
	
	@Column(name="State")
	private String state;
	
	@Column(name="Country")
	private String country;
	
	@Column(name="Zip_Code")
	private String zipCode;
}
