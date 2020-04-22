package com.techhunters.borrowmyproducts.entity;
import java.util.List;

import javax.persistence.CascadeType;
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
	@Column(name="product_id")
	private int productId;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private User user;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="category_id")
	private Category category;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_description")
	private String productDescription;
	
	@Column(name="product_cost")
	private int productCost;
	
	@Column(name="status")
	private String productStatus;
	
	@OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
	private List<ProductRequest> productRequests;
	
	@OneToOne(mappedBy="product",fetch=FetchType.LAZY)
	private ProductLocation productLocation;
	
	
}
