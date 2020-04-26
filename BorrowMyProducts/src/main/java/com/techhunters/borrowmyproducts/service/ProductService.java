package com.techhunters.borrowmyproducts.service;

import java.util.ArrayList;
import java.util.List;

import com.techhunters.borrowmyproducts.dto.ProductDTO;

public interface ProductService {

	List<ProductDTO> listAll();
	
	ProductDTO findById(int id);
	
	void save(ProductDTO productDTO);
	
	void delete(ProductDTO productDTO);
	
	List<ProductDTO> listByCategory(String category);
	
	List<ProductDTO> listByUser(String username);
}
