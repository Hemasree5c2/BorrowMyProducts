package com.techhunters.borrowmyproducts.service;

import java.util.ArrayList;
import java.util.List;

import com.techhunters.borrowmyproducts.dto.ProductDTO;
import com.techhunters.borrowmyproducts.entity.Product;

public interface ProductService {

    List<ProductDTO> listAll();

    ProductDTO findById(int id);

    ProductDTO findByName(String name);

    void save(ProductDTO productDTO);

    void delete(ProductDTO productDTO);

    List<ProductDTO> listByCategoryName(String name);

    List<ProductDTO> listByUserId(int id);

    List<ProductDTO> listAvailableProductsByCategory(String catName, int id);
}
