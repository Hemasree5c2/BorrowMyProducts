package com.techhunters.borrowmyproducts.objectmappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techhunters.borrowmyproducts.dto.ProductDTO;
import com.techhunters.borrowmyproducts.dto.ProductRequestDTO;
import com.techhunters.borrowmyproducts.entity.Product;
import com.techhunters.borrowmyproducts.entity.ProductRequest;

@Component
public class ProductRequestMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProductRequest convertToEntity(ProductRequestDTO productRequestDTO) {

        return modelMapper.map(productRequestDTO, ProductRequest.class);
    }

    public ProductRequestDTO convertToDTO(ProductRequest productRequest) {

        return modelMapper.map(productRequest, ProductRequestDTO.class);
    }

}
