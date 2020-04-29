package com.techhunters.borrowmyproducts.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techhunters.borrowmyproducts.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findByProductName(String name);

}
