package com.neosoft.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.neosoft.bean.User;
import com.neosoft.repository.UserDataSort;
import com.neosoft.repository.UserRepository;

@Service
public class UserService{
	@Autowired
	public UserRepository userRepo;
	
	@Autowired
	public UserDataSort userDataSort;
	
	public List<User> getAllUser(){
		List<User> users =new ArrayList<User>();
		userRepo.findAll().forEach(users::add);
		return users;
	}

	public void addUser(User user) {
		userRepo.save(user);
	}

	public void updateUser(int userId, User user) {
		userRepo.save(user);	
	}

	public void deleteUser(int userId) {
		userRepo.deleteById(userId);
		
	}

	public List<User> getAllUsersByColumns(String field1, String field2) {
		return userDataSort.findAll(Sort.by(Direction.DESC, field1,field2));
	}

	public List<User> getAllUsersByDirections(String dateOfBirth, String joiningDate) {
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.ASC, dateOfBirth));
		orders.add(new Order(Direction.DESC, joiningDate));
		return userDataSort.findAll(Sort.by(orders));
	}

	public List<User> getAllUsersByFirstName(String firstName) {
		return userDataSort.findByFirstName(firstName);
	}

	public List<User> getAllUsersByLastName(String lastName) {
		return userDataSort.findByLastName(lastName);
	}

	public List<User> getAllUsersByPincode(int pincode) {
		return userDataSort.findBypincode(pincode);
	}	
}
