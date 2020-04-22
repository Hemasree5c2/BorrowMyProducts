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

import com.techhunters.borrowmyproducts.entity.User;
import com.techhunters.borrowmyproducts.entity.UserAddress;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
 
 
 
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserAddressRepository userAddressRepository;
    
    @Test
    public void testFindAll() {
       
        User user1=getUser1();
        User user2=getUser2();
        
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(user1);
        expectedUsers.add(user2);

        userRepository.save(user1);
        userRepository.save(user2);
        
        List<User> actualUsers = userRepository.findAll();

        assertThat(actualUsers).isEqualTo(expectedUsers);
    }
    
    @Test
    public void testFindById() {
    	User expectedUser=getUser1();
    	userRepository.save(expectedUser);
    	Optional<User> actualUser=userRepository.findById(expectedUser.getUserId());
    	assertThat(actualUser.get()).isEqualTo(expectedUser);
    }
    
    @Test
    public void testSave() {
    	User expectedUser=getUser1();
    	userRepository.save(expectedUser);
    	Optional<User> actualUser=userRepository.findById(expectedUser.getUserId());
    	assertThat(actualUser.get()).isEqualTo(expectedUser);
    	
    }
    
    @Test
    public void testDelete() {
    	User user=getUser1();
    	userRepository.save(user);
    	Optional<User> actualUser=userRepository.findById(user.getUserId());
    	assertThat(actualUser.get()).isEqualTo(user);
    	userRepository.delete(user);
    	Optional<User> returnedUser=userRepository.findById(user.getUserId());
        assertThat(returnedUser).isEqualTo(Optional.empty());
    	
    }
    
    @Test
    public void testAddress(){
    	UserAddress address=getAddress();
        User user=getUser1();
        address.setUser(user);
        user.setAddress(address);
        userRepository.save(user);
        userAddressRepository.save(address);
        Optional<User> returnedUser=userRepository.findById(user.getUserId());
        UserAddress returnedAddress=returnedUser.get().getAddress();
        assertThat(returnedAddress).isEqualTo(address);
    }
    
    
    
    
    
    public User getUser1() {
    	 User user1=new User();
         user1.setUsername("ray");
         user1.setPassword("password1");
         user1.setPhone("9392081881");
         user1.setEmail("ray@gmail.com");
         return user1;
    }
    
    public User getUser2() {
    	User user2=new User();
        user2.setUsername("penber");
        user2.setPassword("password2");
        user2.setPhone("8736872361");
        user2.setEmail("penber@gmail.com");
        return user2;
   }
   
   public UserAddress getAddress(){
	   UserAddress userAddress=new UserAddress();
	   userAddress.setHouseNo("6");
	   userAddress.setStreetNo("3");;
	   userAddress.setCity("Hyderabad");
	   userAddress.setState("Telangana");
	   userAddress.setCountry("Inida");
	   userAddress.setZipCode("500059");
	   return userAddress;
   }
}

