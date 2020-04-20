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
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone_no")
	private String phone;
	
	@Column(name="email_id")
	private String email;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private List<Product> products;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private List<ProductRequest> productRequests;
	
	@OneToOne(mappedBy="user",fetch=FetchType.LAZY)
	private UserAddress address;
	
    
}
