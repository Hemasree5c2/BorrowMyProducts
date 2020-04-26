package com.techhunters.borrowmyproducts.service;

import java.util.List;

import com.techhunters.borrowmyproducts.dto.ProductRequestDTO;

public interface ProductRequestService {
	

		List<ProductRequestDTO> listAll();
		
		ProductRequestDTO findById(int id);
		
		void save(ProductRequestDTO productDTO);
		
		void delete(ProductRequestDTO productDTO);
		
		List<ProductRequestDTO> listByProduct(String productName);
		
		List<ProductRequestDTO> listByUser(String username);
	
}
