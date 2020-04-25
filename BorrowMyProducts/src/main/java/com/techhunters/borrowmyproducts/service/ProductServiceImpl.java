package com.techhunters.borrowmyproducts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techhunters.borrowmyproducts.daorepository.ProductRepository;
import com.techhunters.borrowmyproducts.dto.ProductDTO;
import com.techhunters.borrowmyproducts.entity.Product;
import com.techhunters.borrowmyproducts.objectmappers.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired 
	private ProductRepository productRepository;
	
	@Autowired 
	private ProductMapper productMapper;
	
	@Override
	public List<ProductDTO> listAll() {
		List<Product> products=productRepository.findAll();
	    List<ProductDTO> productDTOs=new ArrayList<>();
	    ProductDTO tempProductDTO;
	    for(Product product : products) {
	    	tempProductDTO=productMapper.convertToDTO(product);
	    	productDTOs.add(tempProductDTO);
	    }
	    return productDTOs;
	}

	@Override
	public ProductDTO findById(int id) {
		Product product=productRepository.findById(id).get();
		return productMapper.convertToDTO(product);
	}

	@Override
	public void save(ProductDTO productDTO) {
		Product product=productMapper.convertToEntity(productDTO);
		productRepository.save(product);

	}

	@Override
	public void delete(ProductDTO productDTO) {
		Product product=productMapper.convertToEntity(productDTO);
		productRepository.delete(product);

	}

	@Override
	public List<ProductDTO> listByCategory(String category) {
		List<ProductDTO> allProducts=listAll();
		List<ProductDTO> products=new ArrayList<>();
		for(ProductDTO productDTO : allProducts) {
			if(productDTO.getCategory().getCategoryName().equals(category)){
				products.add(productDTO);
			}
		}
		return products;
	}
	
	@Override
	public List<ProductDTO> listByUser(String username) {
		List<ProductDTO> allProducts=listAll();
		List<ProductDTO> products=new ArrayList<>();
		for(ProductDTO productDTO : allProducts) {
			if(productDTO.getUser().getUserName().equals(username)){
				products.add(productDTO);
			}
		}
		return products;
	}

}
 