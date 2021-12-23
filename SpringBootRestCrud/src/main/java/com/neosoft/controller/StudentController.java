package com.neosoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.convertor.StudentConverter;
import com.neosoft.dto.StudentDto;
import com.neosoft.entity.Student;
import com.neosoft.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	public StudentConverter studentConverter;
	
	@PostMapping("/addStudent")
	public StudentDto addStudent(@RequestBody Student student) {
		Student entiry = studentService.addStudent(student);
		return studentConverter.entityToDto(entiry);
	}
	
	@GetMapping("/students")
	public List <StudentDto> getAllstudents()  {
		List<Student> student = studentService.getAllstudents();
		return studentConverter.entityToDto(student);
	}
	
	@GetMapping("/students/{id}")
	public Optional<StudentDto> findByStudentId(@PathVariable int id) {
		Optional<Student> student = studentService.findByStudentId(id);
		return studentConverter.entityToDto(student);
	}
	
	@RequestMapping("/403")
	public String accessDenied() {
	    return "Access Denied - errors:Forbidden/403";
	}
	
}	