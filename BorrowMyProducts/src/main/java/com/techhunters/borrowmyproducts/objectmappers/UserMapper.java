package com.techhunters.borrowmyproducts.objectmappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techhunters.borrowmyproducts.dto.UserDTO;
import com.techhunters.borrowmyproducts.entity.User;

@Component
public class UserMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public User convertToEntity(UserDTO userDTO) {
		
		return modelMapper.map(userDTO,User.class);
	}
	
    public UserDTO convertToDTO(User user) {
		
		return modelMapper.map(user,UserDTO.class);
	}
	
}
