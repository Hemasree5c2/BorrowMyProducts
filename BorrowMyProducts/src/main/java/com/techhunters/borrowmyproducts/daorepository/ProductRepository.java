package com.techhunters.borrowmyproducts.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techhunters.borrowmyproducts.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

}
