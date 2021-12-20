package com.neosoft.SpringBootCrud.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.neosoft.convertor.UserConverter;
import com.neosoft.entity.User;
import com.neosoft.main.SpringBootCrudApplication;
import com.neosoft.repository.UserDataSort;
import com.neosoft.repository.UserRepository;
import com.neosoft.service.UserService;
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootCrudApplication.class)
class SpringBootCrudApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	public UserDataSort userDataSort;
	
	@Autowired
	public UserConverter userConverter;

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
	
	@Test
	public void getAllUsersTest() {
		
		when(userRepository.findAll()).thenReturn(Stream
				.of(new User(101,"Akshay","Karne",new Date(),new Date(),"421202",1),new User(101,"Amit","Karne",new Date(),new Date(),"421202",1)).collect(Collectors.toList()));
		assertEquals(2, userService.getAllUser().size());
	}
	
	/*
	 * @Test public void getAllUsersByFirstNameTest() {
	 * 
	 * String firstName="Akshay"; when(userDataSort.findByFirstName(firstName))
	 * .thenReturn(Stream.of(new User(101,"Amrut","Karne",new Date(),new
	 * Date(),"421206",1)).collect(Collectors.toList()));
	 * 
	 * assertEquals(1, userService.getAllUsersByFirstName(firstName).size());
	 * 
	 * 
	 * }
	 */
	
	/*
	 * @Test public void getUserbyColumnsTest() { Date dateOfBirth = null; Date
	 * joiningDate = null; Date dateOfBirth1 = null; Date joiningDate1 = null; try {
	 * dateOfBirth = formatter.parse("25-08-1990"); joiningDate =
	 * formatter.parse("17-02-2020"); dateOfBirth1 = formatter.parse("25-08-1995");
	 * joiningDate1 = formatter.parse("17-02-2021"); } catch (ParseException e1) {
	 * e1.printStackTrace(); }
	 * 
	 * when(userDataSort.findAll(Sort.by(Direction.DESC,
	 * "25-08-1990","17-02-2020"))) .thenReturn(Stream.of(new
	 * User(101,"Akshay","Karne",dateOfBirth,joiningDate,"421202",1),new
	 * User(101,"Amit","Karne",dateOfBirth1,joiningDate1,"421202",1)).collect(
	 * Collectors.toList())); assertEquals(2, userService.getAllUser().size()); }
	 */
	
	@Test
	public void addUserTest() {
		User user = new User(101,"Akshay","Karne",new Date(),new Date(),"421206",1);
		userService.addUser(user);
		verify(userRepository,times(1)).save(user);
	}
	
	@Test
	public void updateUserTest() {
		int userId=101;
		User user = new User(101,"Akshay","Pise",new Date(),new Date(),"421206",1);
		userService.updateUser(userId,user);
		verify(userRepository,times(1)).save(user);
	}
	
	@Test
	public void deleteUserTest() {
		int userId=101;
		User user = new User(101,"Akshay","Pise",new Date(),new Date(),"421206",1);
		userService.deleteUser(userId);
		verify(userRepository,times(1)).deleteById(userId);
	}
}
