package com.techhunters.borrowmyproducts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techhunters.borrowmyproducts.daorepository.ProductRequestRepository;
import com.techhunters.borrowmyproducts.dto.ProductRequestDTO;
import com.techhunters.borrowmyproducts.entity.ProductRequest;
import com.techhunters.borrowmyproducts.objectmappers.ProductRequestMapper;

@Service
public class ProductRequestServiceImpl implements ProductRequestService {

	@Autowired 
	private ProductRequestRepository productRequestRepository;
	
	@Autowired 
	private ProductRequestMapper productRequestMapper;
	
	@Override
	public List<ProductRequestDTO> listAll() {
		List<ProductRequest> productRequests=productRequestRepository.findAll();
	    List<ProductRequestDTO> productRequestDTOs=new ArrayList<>();
	    ProductRequestDTO tempProductRequestDTO;
	    for(ProductRequest productRequest : productRequests) {
	    	tempProductRequestDTO=productRequestMapper.convertToDTO(productRequest);
	    	productRequestDTOs.add(tempProductRequestDTO);
	    }
	    return productRequestDTOs;
	}

	@Override
	public ProductRequestDTO findById(int id) {
		ProductRequest productRequest=productRequestRepository.findById(id).get();
		return productRequestMapper.convertToDTO(productRequest);
	}

	@Override
	public void save(ProductRequestDTO productRequestDTO) {
		ProductRequest productRequest=productRequestMapper.convertToEntity(productRequestDTO);
		productRequestRepository.save(productRequest);

	}

	@Override
	public void delete(ProductRequestDTO productRequestDTO) {
		ProductRequest productRequest=productRequestMapper.convertToEntity(productRequestDTO);
		productRequestRepository.delete(productRequest);

	}

	@Override
	public List<ProductRequestDTO> listByProduct(String productName) {
		List<ProductRequestDTO> allProductRequests=listAll();
		List<ProductRequestDTO> productRequests=new ArrayList<>();
		for(ProductRequestDTO productRequestDTO : allProductRequests) {
			if(productRequestDTO.getProduct().getProductName().equals(productName)){
				productRequests.add(productRequestDTO);
			}
		}
		return productRequests;
	}
	
	@Override
	public List<ProductRequestDTO> listByUser(String username) {
		List<ProductRequestDTO> allProductRequests=listAll();
		List<ProductRequestDTO> productRequests=new ArrayList<>();
		for(ProductRequestDTO productRequestDTO : allProductRequests) {
			if(productRequestDTO.getUser().getUserName().equals(username)){
				productRequests.add(productRequestDTO);
			}
		}
		return productRequests;
	}

}