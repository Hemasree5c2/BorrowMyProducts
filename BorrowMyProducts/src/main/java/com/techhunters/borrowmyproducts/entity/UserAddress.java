package com.techhunters.borrowmyproducts.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
   

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_id")
	private int userAddressId;
	
	@OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
	
	@Column(name="house_no")
	private String houseNo;
	
	@Column(name="street_no")
	private String StreetNo;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	
	@Column(name="zip_code")
	private String zipCode;
}
