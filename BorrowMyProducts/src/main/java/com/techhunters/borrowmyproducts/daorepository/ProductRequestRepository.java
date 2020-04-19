package com.techhunters.borrowmyproducts.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techhunters.borrowmyproducts.entity.ProductRequest;

public interface ProductRequestRepository extends JpaRepository<ProductRequest,Integer> {

}
