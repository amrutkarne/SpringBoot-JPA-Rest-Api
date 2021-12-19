package com.neosoft.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.bean.User;
import com.neosoft.service.UserService;

@RestController
public class UserController {
	@Autowired
	public UserService userService;
	
	@RequestMapping("/users")
	public List<User> getAllUsers(){
		System.out.println("********Inside Get method********");
		return userService.getAllUser();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		System.out.println("********Inside Post method********");
		userService.addUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/users/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable int userId,@Valid @RequestBody User user) {
		System.out.println("********Inside PUT method********");
		userService.updateUser(userId,user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		System.out.println("********Inside Delete method********");
		userService.deleteUser(userId);
	}
	
	@RequestMapping("/usersByColumns")
	public List<User> getAllUsersByColumns(@RequestParam String field1, @RequestParam String field2){
		System.out.println("********Inside Get method getAllUsersByColumns********");
		return userService.getAllUsersByColumns(field1,field2);
	}
	
	@RequestMapping("/usersByDirections")
	public List<User> getAllUsersByDirections(@RequestParam String field1, @RequestParam String field2){
		System.out.println("********Inside Get method getAllUsersByDirections********");
		return userService.getAllUsersByDirections(field1,field2);
	}
	
	@RequestMapping("/usersByFirstName")
	public List<User> getAllUsersByFirstName(@RequestParam String firstName){
		System.out.println("********Inside Get method getAllUsersByFirstName********");
		return userService.getAllUsersByFirstName(firstName);
	}
	
	@RequestMapping("/usersByLastName")
	public List<User> getAllUsersByLastName(@RequestParam String lastName){
		System.out.println("********Inside Get method getAllUsersByLastName********");
		return userService.getAllUsersByLastName(lastName);
	}
	
	@RequestMapping("/usersByPincode")
	public List<User> getAllUsersByPincode(@RequestParam int pincode){
		System.out.println("********Inside Get method getAllUsersByPincode********");
		return userService.getAllUsersByPincode(pincode);
	}
 }
