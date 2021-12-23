package com.neosoft.convertor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.neosoft.dto.StudentDto;
import com.neosoft.entity.Student;
@Component
public class StudentConverter {
	
	public StudentDto entityToDto(Student student) {
		ModelMapper mapper=new ModelMapper();
		StudentDto dto = mapper.map(student, StudentDto.class);
		return dto;
		
	}
	
	public List<StudentDto> entityToDto(List<Student> student) {
		return student.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
		
	}
	
	public Student dtoToEntity(StudentDto studentDto) {
		ModelMapper mapper=new ModelMapper();
		Student student = mapper.map(studentDto, Student.class);
		return student;
		
	}
	
	public List<Student> dtoToEntity(List<StudentDto> studentDto) {
		return studentDto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());	
	}

	public Optional<StudentDto> entityToDto(Optional<Student> student) {
		ModelMapper mapper=new ModelMapper();
		StudentDto dto = mapper.map(student, StudentDto.class);
		return Optional.of(dto);
	}
	
	

}
