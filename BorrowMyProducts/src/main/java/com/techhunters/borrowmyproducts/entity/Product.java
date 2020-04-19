package com.techhunters.borrowmyproducts.entity;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="Product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Product_Id")
	private int productId;
	
	@ManyToOne
    @JoinColumn(name="User_Id")
    private User user;
	
	@ManyToOne
	@JoinColumn(name="Category_Id")
	private Category category;
	
	@Column(name="Product_Name")
	private String productName;
	
	@Column(name="Product_Description")
	private String productDescription;
	
	@Column(name="Product_Cost")
	private int productCost;
	
	@Column(name="Status")
	private String productStatus;
	
	@OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
	private List<ProductRequest> productRequests;
	
	@OneToOne(mappedBy="product",fetch=FetchType.LAZY)
	private ProductLocation productLocation;
	
	
}
