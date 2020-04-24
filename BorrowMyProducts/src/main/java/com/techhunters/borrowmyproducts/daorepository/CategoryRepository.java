package com.techhunters.borrowmyproducts.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techhunters.borrowmyproducts.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
