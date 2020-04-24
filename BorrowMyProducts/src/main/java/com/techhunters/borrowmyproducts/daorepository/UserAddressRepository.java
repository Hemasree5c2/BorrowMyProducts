package com.techhunters.borrowmyproducts.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techhunters.borrowmyproducts.entity.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress,Integer> {

}
