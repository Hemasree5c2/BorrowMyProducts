package com.techhunters.borrowmyproducts.daorepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.techhunters.borrowmyproducts.entity.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
	 
	    @Autowired
	    private CategoryRepository categoryRepository;
	    
	    
	    @Test
	    public void testFindAll() {
	       
	        Category category1=getCategory1();
	        Category category2=getCategory2();
	        
	        List<Category> expectedCategories = new ArrayList<>();
	        expectedCategories.add(category1);
	        expectedCategories.add(category2);

	        categoryRepository.save(category1);
	        categoryRepository.save(category2);
	        
	        List<Category> actualCategories = categoryRepository.findAll();

	        assertThat(actualCategories).isEqualTo(expectedCategories);
	    }
	    
	    @Test
	    public void testFindById() {
	    	Category expectedCategory=getCategory1();
	    	categoryRepository.save(expectedCategory);
	    	Optional<Category> actualCategory=categoryRepository.findById(expectedCategory.getCategoryId());
	    	assertThat(actualCategory.get()).isEqualTo(expectedCategory);
	    }
	    
	    @Test
	    public void testSave() {
	    	Category expectedCategory=getCategory1();
	    	categoryRepository.save(expectedCategory);
	    	Optional<Category> actualCategory=categoryRepository.findById(expectedCategory.getCategoryId());
	    	assertThat(actualCategory.get()).isEqualTo(expectedCategory);
	    }
	    
	    @Test
	    public void testDelete() {
	    	Category category=getCategory1();
	    	categoryRepository.save(category);
	    	Optional<Category> actualCategory=categoryRepository.findById(category.getCategoryId());
	    	assertThat(actualCategory.get()).isEqualTo(category);
	    	categoryRepository.delete(category);
	    	Optional<Category> returnedCategory=categoryRepository.findById(category.getCategoryId());
	        assertThat(returnedCategory).isEqualTo(Optional.empty());
	    	
	    }
	    
	    
	    public Category getCategory1() {
	    	Category category=new Category();
	    	category.setCategoryName("Electronics");
	    	return category;
	    }
	    
	    public Category getCategory2() {
	    	Category category=new Category();
	    	category.setCategoryName("Furniture");
	    	return category;
	    }
}
