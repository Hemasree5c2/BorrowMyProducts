package com.techhunters.borrowmyproducts.service;


import com.techhunters.borrowmyproducts.daorepository.ProductRepository;
import com.techhunters.borrowmyproducts.dto.ProductDTO;
import com.techhunters.borrowmyproducts.dto.UserDTO;
import com.techhunters.borrowmyproducts.entity.Product;
import com.techhunters.borrowmyproducts.objectmappers.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRequestService productRequestService;
    @Autowired
    private UserService userService;

    @Override
    public List<ProductDTO> listAll() {
        List<ProductDTO> productDTOs = new ArrayList<>();
        List<Product> products = (List<Product>) productRepository.findAll();
        for (Product product : products) {
            ProductDTO productDTO = productMapper.convertToDTO(product);
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    @Override
    public ProductDTO findById(int id) {
        Product product = productRepository.findById(id).get();
        return productMapper.convertToDTO(product);
    }

    @Override
    public ProductDTO findByName(String name) {
        return productMapper.convertToDTO(productRepository.findByProductName(name));
    }


    @Override
    public void save(ProductDTO productDTO) {
        productRepository.save(productMapper.convertToEntity(productDTO));

    }

    @Override
    public void delete(ProductDTO productDTO) {
        productRepository.delete(productMapper.convertToEntity(productDTO));

    }

    @Override
    public List<ProductDTO> listByCategoryName(String name) {
        List<ProductDTO> allProducts = listAll();
        List<ProductDTO> products = new ArrayList<>();
        for (ProductDTO productDTO : allProducts) {
            if (productDTO.getCategory().getCategoryName().equals(name)) {
                products.add(productDTO);
            }
        }
        return products;
    }

    @Override
    public List<ProductDTO> listByUserId(int id) {
        List<ProductDTO> allProducts = listAll();
        List<ProductDTO> products = new ArrayList<>();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getUser().getUserId() == (id)) {
                products.add(allProducts.get(i));
            }
        }
        return products;
    }

    @Override
    public List<Product> listAvailableProductsByCategory(String catName, int id) {
        List<ProductDTO> products = listByCategoryName(catName);
        List<Integer> requests = productRequestService.listByUserId(id);
        List<Product> available = new ArrayList<>();
        for (ProductDTO productDTO : products) {
            if (productDTO.getProductStatus().equals("available") && productDTO.getUser().getUserId() != id && !requests.contains(productDTO.getProductId())) {
                available.add(productMapper.convertToEntity(productDTO));
            }
        }
        return available;
    }

    @Override
    public List<ProductDTO> listAvailableProductsByCategoryLocation(String catName, int id) {
        List<Product> availableCategory = listAvailableProductsByCategory(catName, id);
        UserDTO user = userService.findById(id);
        List<ProductDTO> result = new ArrayList<>();
        for (Product product : availableCategory) {
            Double dist = distance(product.getProductLocation().getLatitude(), user.getAddress().getLatitude(), product.getProductLocation().getLongitude(), user.getAddress().getLongitude());
            if (dist < 5) {
                result.add(productMapper.convertToDTO(product));
            }
        }
        return result;
    }

    @Override
    public Double distance(double lat1, double lat2, double lon1, double lon2) {
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;
        return (c * r);
    }

}
 