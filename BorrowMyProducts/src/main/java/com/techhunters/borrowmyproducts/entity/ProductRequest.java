package com.techhunters.borrowmyproducts.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="ProductRequest")
public class ProductRequest {
   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Product_Request_id")
	private int productRequestId;
	
	@ManyToOne
	@JoinColumn(name="User_uid")
	private int requesterId;
	
	@ManyToOne
	@JoinColumn(name="Product_pid")
	private int productId;
	
}
