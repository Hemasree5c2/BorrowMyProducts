package com.techhunters.borrowmyproducts.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techhunters.borrowmyproducts.entity.ProductLocation;
import org.springframework.data.repository.CrudRepository;

public interface ProductLocationRepository extends CrudRepository<ProductLocation, Integer> {

}
