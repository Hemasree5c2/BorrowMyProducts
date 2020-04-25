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
import com.techhunters.borrowmyproducts.entity.ProductRequest;
import com.techhunters.borrowmyproducts.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRequestRepositoryTest {
   
	
 
    @Autowired
    private ProductRequestRepository productRequestRepository;
    
   
    @Test
    public void testFindAll() {
       
        ProductRequest productRequest1=getProductRequest1();
        ProductRequest productRequest2=getProductRequest2();
        
        List<ProductRequest> expectedProductRequests = new ArrayList<>();
        expectedProductRequests.add(productRequest1);
        expectedProductRequests.add(productRequest2);

        productRequestRepository.save(productRequest1);
        productRequestRepository.save(productRequest2);
        
        List<ProductRequest> actualProductRequests = productRequestRepository.findAll();

        assertThat(actualProductRequests).isEqualTo(expectedProductRequests);
    }
    
    @Test
    public void testFindById() {
    	ProductRequest expectedProductRequest=getProductRequest1();
    	productRequestRepository.save(expectedProductRequest);
    	Optional<ProductRequest> actualProductRequest=productRequestRepository.findById(expectedProductRequest.getProductRequestId());
    	assertThat(actualProductRequest.get()).isEqualTo(expectedProductRequest);
    }
    
    @Test
    public void testSave() {
    	ProductRequest expectedProductRequest=getProductRequest1();
    	productRequestRepository.save(expectedProductRequest);
    	Optional<ProductRequest> actualProductRequest=productRequestRepository.findById(expectedProductRequest.getProductRequestId());
    	assertThat(actualProductRequest.get()).isEqualTo(expectedProductRequest);
    }
    
    @Test
    public void testDelete() {
    	ProductRequest productRequest=getProductRequest1();
    	productRequestRepository.save(productRequest);
    	Optional<ProductRequest> actualProductRequest=productRequestRepository.findById(productRequest.getProductRequestId());
    	assertThat(actualProductRequest.get()).isEqualTo(productRequest);
    	productRequestRepository.delete(productRequest);
    	Optional<ProductRequest> returnedProductRequest=productRequestRepository.findById(productRequest.getProductRequestId());
        assertThat(returnedProductRequest).isEqualTo(Optional.empty());
    	
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
	    
	    public ProductRequest getProductRequest1() {
	    	ProductRequest productRequest=new ProductRequest();
	    	productRequest.setUser(getUser());
	    	productRequest.setProduct(getProduct1());
	    	return productRequest;
	    }
	    
	    public ProductRequest getProductRequest2() {
	    	ProductRequest productRequest=new ProductRequest();
	    	productRequest.setUser(getUser());
	    	productRequest.setProduct(getProduct2());
	    	return productRequest;
	    }
	    
}
