package com.techhunters.borrowmyproducts.daorepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.techhunters.borrowmyproducts.entity.Category;
import com.techhunters.borrowmyproducts.entity.Product;
import com.techhunters.borrowmyproducts.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    
	 
	    @Autowired
	    private ProductRepository productRepository;
	   
	    
	    @Test
	    public void testFindAll() {
	       
	        Product product1=getProduct1();
	        Product product2=getProduct2();
	        
	        List<Product> expectedProducts = new ArrayList<>();
	        expectedProducts.add(product1);
	        expectedProducts.add(product2);

	        productRepository.save(product1);
	        productRepository.save(product2);
	        
	        List<Product> actualProducts = productRepository.findAll();

	        assertThat(actualProducts).isEqualTo(expectedProducts);
	    }
	    
	    @Test
	    public void testFindById() {
	    	Product expectedProduct=getProduct1();
	    	productRepository.save(expectedProduct);
	    	Optional<Product> actualProduct=productRepository.findById(expectedProduct.getProductId());
	    	assertThat(actualProduct.get()).isEqualTo(expectedProduct);
	    }
	    
	    @Test
	    public void testSave() {
	    	Product expectedProduct=getProduct1();
	    	productRepository.save(expectedProduct);
	    	Optional<Product> actualProduct=productRepository.findById(expectedProduct.getProductId());
	    	assertThat(actualProduct.get()).isEqualTo(expectedProduct);
	    }
	    
	    @Test
	    public void testDelete() {
	    	Product product=getProduct1();
	    	productRepository.save(product);
	    	Optional<Product> actualProduct=productRepository.findById(product.getProductId());
	    	assertThat(actualProduct.get()).isEqualTo(product);
	    	productRepository.delete(product);
	    	Optional<Product> returnedProduct=productRepository.findById(product.getProductId());
	        assertThat(returnedProduct).isEqualTo(Optional.empty());
	    	
	    }
	    
	    
	    public Category getCategory() {
	    	Category category=new Category();
	    	category.setCategoryName("Electronics");
	    	return category;
	    }
	    
	    public User getUser() {
	    	 User user1=new User();
	         user1.setUserFirstName("ray");
	         user1.setPassword("password1");
	         user1.setPhone("9392081881");
	         user1.setEmail("ray@gmail.com");
	         return user1;
	    }
	    
	    public Product getProduct1() {
	    	Product product=new Product();
	    	product.setProductName("Camera");
	    	product.setProductDescription("Canon Mark III");
	    	product.setProductCost(1000);
	    	product.setProductStatus("Available");
	    	product.setCategory(getCategory());
	    	product.setUser(getUser());
	    	return product;
	    }
	    
	    public Product getProduct2() {
	    	Product product=new Product();
	    	product.setProductName("Laptop");
	    	product.setProductDescription("8GB RAM");
	    	product.setProductCost(2000);
	    	product.setProductStatus("Available");
	    	product.setCategory(getCategory());
	    	product.setUser(getUser());
	    	return product;
	    }
	    
	
}
