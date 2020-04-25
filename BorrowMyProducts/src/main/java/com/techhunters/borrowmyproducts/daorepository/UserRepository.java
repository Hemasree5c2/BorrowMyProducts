package com.techhunters.borrowmyproducts.daorepository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.techhunters.borrowmyproducts.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
	User findByEmail(String email);
	
	User findByUserName(String userName);
}
