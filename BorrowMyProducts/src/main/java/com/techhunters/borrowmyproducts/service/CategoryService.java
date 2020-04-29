package com.techhunters.borrowmyproducts.service;

import com.techhunters.borrowmyproducts.dto.CategoryDTO;
import com.techhunters.borrowmyproducts.dto.UserDTO;
import com.techhunters.borrowmyproducts.entity.Category;
import com.techhunters.borrowmyproducts.entity.User;

import java.util.List;

public interface CategoryService {
    public void save(CategoryDTO categoryDTO);

    public List<CategoryDTO> findAll();

    public CategoryDTO findById(int id);

    public CategoryDTO findByCategoryName(String name);

    public void delete(CategoryDTO category);
}
