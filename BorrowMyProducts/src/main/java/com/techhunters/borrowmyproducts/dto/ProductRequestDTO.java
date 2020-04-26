package com.techhunters.borrowmyproducts.dto;

import com.techhunters.borrowmyproducts.entity.Product;
import com.techhunters.borrowmyproducts.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequestDTO {

	private UserDTO user;
	
	private ProductDTO product;
}
