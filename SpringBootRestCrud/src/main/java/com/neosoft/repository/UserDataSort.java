package com.neosoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.bean.User;
@Repository
public interface UserDataSort extends JpaRepository<User, Integer>{
	List<User> findByFirstName(String firstName); 
	List<User> findByLastName(String lastName);  
	List<User> findBypincode(int pincode);  
}
