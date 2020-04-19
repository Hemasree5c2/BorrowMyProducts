package com.techhunters.borrowmyproducts.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.tomcat.jni.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="User")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="User_Id")
	private int userId;
	
	@Column(name="User_Name")
	private String username;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Phone_no")
	private String phone;
	
	@Column(name="Email_Id")
	private String email;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private List<Product> products;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private List<ProductRequest> productRequests;
	
	@OneToOne(mappedBy="user",fetch=FetchType.LAZY)
	private UserAddress address;
	
    
}
