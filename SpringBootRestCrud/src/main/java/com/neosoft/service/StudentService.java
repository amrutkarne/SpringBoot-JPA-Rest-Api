package com.neosoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.neosoft.entity.Student;
import com.neosoft.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

	public List <Student> getAllstudents() {
		return studentRepository.findAll();
	}

	public Optional<Student> findByStudentId(int id) {
		return studentRepository.findById(id);
	}

}
