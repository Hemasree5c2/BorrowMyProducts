package com.techhunters.borrowmyproducts.service;

import java.util.ArrayList;
import java.util.List;

import com.techhunters.borrowmyproducts.entity.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import com.techhunters.borrowmyproducts.daorepository.UserRepository;
import com.techhunters.borrowmyproducts.dto.ProductRequestDTO;
import com.techhunters.borrowmyproducts.dto.UserDTO;
import com.techhunters.borrowmyproducts.entity.User;
import com.techhunters.borrowmyproducts.objectmappers.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductRequestService productRequestService;

    @Override
    public void save(UserDTO userDTO) {

        User user = userMapper.convertToEntity(userDTO);
        userRepository.save(user);

    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOs = new ArrayList<>();
        List<User> users = (List<User>) userRepository.findAll();
        for (User user : users) {
            UserDTO userDTO = userMapper.convertToDTO(user);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public UserDTO findById(int id) {
        return userMapper.convertToDTO(userRepository.findById(id).get());
    }

    @Override
    public void delete(UserDTO user) {
        userRepository.delete(userMapper.convertToEntity(user));

    }

    @Override
    public UserDTO findByEmail(String email) {
        return userMapper.convertToDTO(userRepository.findByEmail(email));
    }

//	@Override
//	public User findByUserName(String userName) {
//
//		return userRepository.findByUserName(userName);
//
//	}
}