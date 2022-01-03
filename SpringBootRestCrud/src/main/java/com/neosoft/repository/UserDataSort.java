package com.neosoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.entity.User;
@Repository
public interface UserDataSort extends JpaRepository<User, Integer>{
	List<User> findByFirstName(String firstName); 
	List<User> findByLastName(String lastName);  
	List<User> findBypincode(String pincode);  
	User findById(int id);  
}
