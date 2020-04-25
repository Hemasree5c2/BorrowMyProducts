package com.techhunters.borrowmyproducts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techhunters.borrowmyproducts.daorepository.UserRepository;
import com.techhunters.borrowmyproducts.dto.UserDTO;
import com.techhunters.borrowmyproducts.entity.User;
import com.techhunters.borrowmyproducts.objectmappers.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void save(UserDTO userDTO) {
		
		User user=userMapper.convertToEntity(userDTO);
		userRepository.save(user);
		
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int id) {
	    return userRepository.findById(id).get();
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
		
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByUserName(String userName) {
		
		return userRepository.findByUserName(userName);
	
	}
}
