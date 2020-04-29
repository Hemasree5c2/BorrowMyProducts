package com.techhunters.borrowmyproducts.service;

import java.util.List;

import com.techhunters.borrowmyproducts.dto.ProductDTO;
import com.techhunters.borrowmyproducts.dto.ProductRequestDTO;
import com.techhunters.borrowmyproducts.entity.Product;
import com.techhunters.borrowmyproducts.entity.ProductRequest;

public interface ProductRequestService {


    List<ProductRequestDTO> listAll();

    ProductRequestDTO findById(int id);

    void save(ProductRequestDTO productDTO);

    void delete(ProductRequestDTO productDTO);

    List<ProductRequestDTO> listByProduct(int id);

    List<ProductRequestDTO> listByUser(String username);

    List<Integer> listByUserId(int id);

    List<ProductDTO> listProductsByUser(String name);

    List<ProductRequestDTO> listByProductPending(int id);

}
