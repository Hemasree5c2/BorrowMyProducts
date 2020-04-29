package com.techhunters.borrowmyproducts.service;

import java.util.List;

import com.techhunters.borrowmyproducts.dto.UserDTO;
import com.techhunters.borrowmyproducts.entity.User;

public interface UserService {


    public void save(UserDTO user);

    public List<UserDTO> findAll();

    public UserDTO findById(int id);

    public void delete(UserDTO user);

    public UserDTO findByEmail(String email);


//	public User findByUserName(String userName);

}
