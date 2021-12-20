package com.neosoft.convertor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.neosoft.dto.UserDto;
import com.neosoft.entity.User;

@Component
public class UserConverter {

	public UserDto entityToDto(User user) {
		/*
		 * UserDto dto= new UserDto(); dto.setUserId(user.getUserId());
		 * dto.setFirstName(user.getFirstName()); dto.setLastName(user.getLastName());
		 * dto.setDateOfBirth(user.getDateOfBirth());
		 * dto.setJoiningDate(user.getJoiningDate()); dto.setPincode(user.getPincode());
		 * dto.setCvoid(user.getCvoid());
		 */
		ModelMapper mapper=new ModelMapper();
		UserDto dto = mapper.map(user, UserDto.class);
		return dto;
		
	}
	
	public List<UserDto> entityToDto(List<User> user) {
		return user.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
		
	}
	
	public User dtoToEntity(UserDto userDto) {
		/*
		 * User user= new User(); user.setUserId(userDto.getUserId());
		 * user.setFirstName(userDto.getFirstName());
		 * user.setLastName(userDto.getLastName());
		 * user.setDateOfBirth(userDto.getDateOfBirth());
		 * user.setJoiningDate(userDto.getJoiningDate());
		 * user.setPincode(userDto.getPincode()); user.setCvoid(userDto.getCvoid());
		 */
		ModelMapper mapper=new ModelMapper();
		User user = mapper.map(userDto, User.class);
		return user;
		
	}
	
	public List<User> dtoToEntity(List<UserDto> userDto) {
		return userDto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());	
	}
	
	
}
