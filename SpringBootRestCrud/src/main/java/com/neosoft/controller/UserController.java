package com.neosoft.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.convertor.UserConverter;
import com.neosoft.dto.UserDto;
import com.neosoft.entity.User;
import com.neosoft.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class UserController {
	@Autowired
	public UserService userService;
	
	@Autowired
	public UserConverter userConverter;
	
	@RequestMapping("/users")
	public List<UserDto> getAllUsers(){
		log.info("********Inside getAllUsers method********");
		List<User> user = userService.getAllUser();
		return userConverter.entityToDto(user);
	}
	
	@RequestMapping("/users/{userId}")
	public UserDto getUsersById(@PathVariable int userId){
		log.info("********Inside getUsersById method********");
		User user = userService.getUsersById(userId);
		return userConverter.entityToDto(user);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/users")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody User user) {
		log.info("********Inside addUser method********");
		userService.addUser(user);
		return new ResponseEntity<UserDto>(userConverter.entityToDto(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/users/{userId}")
	public ResponseEntity<UserDto> updateUser(@PathVariable int userId,@Valid @RequestBody User user) {
		log.info("********Inside updateUser method********");
		userService.updateUser(userId,user);
		return new ResponseEntity<UserDto>(userConverter.entityToDto(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		log.info("********Inside deleteUser method********");
		userService.deleteUser(userId);
	}
	
	@RequestMapping("/usersByColumns")
	public List<UserDto> getAllUsersByColumns(@RequestParam String field1, @RequestParam String field2){
		log.info("********Inside method getAllUsersByColumns********");
		List<User> user = userService.getAllUsersByColumns(field1,field2);
		return userConverter.entityToDto(user);
	}
	
	@RequestMapping("/usersByDirections")
	public List<UserDto> getAllUsersByDirections(@RequestParam String field1, @RequestParam String field2){
		log.info("********Inside method getAllUsersByDirections********");
		List<User> user = userService.getAllUsersByDirections(field1,field2);
		return userConverter.entityToDto(user);
	}
	
	@RequestMapping("/usersByFirstName")
	public List<UserDto> getAllUsersByFirstName(@RequestParam String firstName){
		log.info("********Inside method getAllUsersByFirstName********");
		List<User> user = userService.getAllUsersByFirstName(firstName);
		return userConverter.entityToDto(user);
	}
	
	@RequestMapping("/usersByLastName")
	public List<UserDto> getAllUsersByLastName(@RequestParam String lastName){
		log.info("********Inside method getAllUsersByLastName********");
		List<User> user = userService.getAllUsersByLastName(lastName);
		return userConverter.entityToDto(user);
	}
	
	@RequestMapping("/usersByPincode")
	public List<UserDto> getAllUsersByPincode(@RequestParam String pincode){
		log.info("********Inside method getAllUsersByPincode********");
		List<User> user = userService.getAllUsersByPincode(pincode);
		return userConverter.entityToDto(user);
	}
 }
