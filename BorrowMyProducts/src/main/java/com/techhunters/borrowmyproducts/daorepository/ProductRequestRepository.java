package com.techhunters.borrowmyproducts.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techhunters.borrowmyproducts.entity.ProductRequest;
import org.springframework.data.repository.CrudRepository;

public interface ProductRequestRepository extends CrudRepository<ProductRequest, Integer> {

}
