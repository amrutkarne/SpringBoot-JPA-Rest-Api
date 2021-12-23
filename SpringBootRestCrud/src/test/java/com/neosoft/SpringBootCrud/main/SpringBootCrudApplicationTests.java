package com.neosoft.SpringBootCrud.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.junit4.SpringRunner;

import com.neosoft.convertor.UserConverter;
import com.neosoft.entity.Project;
import com.neosoft.entity.Student;
import com.neosoft.entity.User;
import com.neosoft.main.SpringBootCrudApplication;
import com.neosoft.repository.StudentRepository;
import com.neosoft.repository.UserDataSort;
import com.neosoft.repository.UserRepository;
import com.neosoft.service.StudentService;
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
	
	@MockBean
	public UserDataSort userDataSort;
	
	@MockBean
	public StudentRepository studentRepository;
	
	@Autowired
	private StudentService studentService;

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
	
	/***************************************************************************************
		Task-1 Create User Registration CRUD API 
	 ***************************************************************************************/
	
	@Test
	public void getAllUsersTest() {
		
		when(userRepository.findAll()).thenReturn(Stream
				.of(new User(101,"Akshay","Karne",new Date(),new Date(),"421202",1),new User(101,"Amit","Karne",new Date(),new Date(),"421202",1)).collect(Collectors.toList()));
		assertEquals(2, userService.getAllUser().size());
	}
	
	
	
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
	
	@Test
	public void getAllUsersByFirstNameTest() {
		List<User> user = List.of(new User(101,"Akshay","Pise",new Date(),new Date(),"421206",1));
		
		when(userDataSort.findByFirstName("Akshay")).thenReturn(user);
		assertThat(userService.getAllUsersByFirstName("Akshay")).isEqualTo(user);
	}
	
	@Test
	public void getAllUsersByLastNameTest() {
		List<User> user = List.of(new User(101,"Akshay","Pise",new Date(),new Date(),"421206",1));
		
		when(userDataSort.findByLastName("Pise")).thenReturn(user);
		assertThat(userService.getAllUsersByLastName("Pise")).isEqualTo(user);
	}
	
	@Test
	public void getAllUsersByPincodeTest() {
		List<User> user = List.of(new User(101,"Akshay","Pise",new Date(),new Date(),"421206",1),new User(101,"Amit","Karne",new Date(),new Date(),"421202",1));
		
		when(userDataSort.findBypincode("421206")).thenReturn(user);
		assertThat(userService.getAllUsersByPincode("421206")).isEqualTo(user);
	}
	
	
	@Test
	public void getAllUsersByDirectionsTest() {
		Date dob=java.sql.Date.valueOf(LocalDate.of(1990, 8, 25));
		//Date dob1=java.sql.Date.valueOf(LocalDate.of(1995, 10, 15));
		Date joiningDt=java.sql.Date.valueOf(LocalDate.of(2015,6,14));
		//Date joiningDt1=java.sql.Date.valueOf(LocalDate.of(2020,6,29));
		
		//List<User> user = List.of(new User(101,"Akshay","Pise",dob,joiningDt,"421206",1),new User(101,"Amit","Karne",dob1,joiningDt1,"421202",1));
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.ASC, String.valueOf(dob)));
		orders.add(new Order(Direction.DESC, String.valueOf(joiningDt)));
		List<User> users = userDataSort.findAll(Sort.by(orders));
	    assertThat(userService.getAllUsersByDirections(String.valueOf(dob),String.valueOf(joiningDt))).isEqualTo(users);
	}
	
	@Test
	public void getAllUsersByColumnsTest() {
		Date dob=java.sql.Date.valueOf(LocalDate.of(1990, 8, 25));
		//Date dob1=java.sql.Date.valueOf(LocalDate.of(1995, 10, 15));
		Date joiningDt=java.sql.Date.valueOf(LocalDate.of(2015,6,14));
		//Date joiningDt1=java.sql.Date.valueOf(LocalDate.of(2020,6,29));
		
		//List<User> user = List.of(new User(101,"Akshay","Pise",dob,joiningDt,"421206",1),new User(101,"Amit","Karne",dob1,joiningDt1,"421202",1));
		
		List<User> users = userDataSort.findAll(Sort.by(Direction.DESC, String.valueOf(dob),String.valueOf(joiningDt)));
	    assertThat(userService.getAllUsersByColumns(String.valueOf(dob),String.valueOf(joiningDt))).isEqualTo(users);
	}
	/***************************************************************************************
	 					Task-2 Create Student-Project Mapping API 
	 ***************************************************************************************/
	
	
	@Test
	public void getAllStudentsTest() {
		
		when(studentRepository.findAll()).thenReturn(Stream
				.of(new Student(1,"Amrut","Karne","9876543210","amrut@gmail.com",List.of(new Project(11,"POC1",10L),new Project(12,"POC2",20L))),
					new Student(1,"Suresh","Karne","9876587610","suresh@gmail.com",List.of(new Project(13,"POC3",10L),new Project(14,"POC4",20L)))
).collect(Collectors.toList()));
		assertEquals(2, studentService.getAllstudents().size());
	}
	
	@Test
	public void addStudentTest() {
		Student user = new Student(1,"Komal","Karne","9823443210","komal@gmail.com",List.of(new Project(15,"POC5",15L)));
		studentService.addStudent(user);
		verify(studentRepository,times(1)).save(user);
	}
	
	@Test
	public void findByStudentIdTest() {
		Optional<Student> student = Optional.of(new Student(1,"Komal","Karne","9823443210","komal@gmail.com",List.of(new Project(15,"POC5",15L))));
		
		when(studentRepository.findById(1)).thenReturn(student);
		assertThat(studentService.findByStudentId(1)).isEqualTo(student);
	}
	
	
}
