package com.neosoft.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.neosoft.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})  
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty(message = "First name is required")
	private String firstName;
	
	@NotEmpty(message = "Last name is required")
	private String lastName;
	
	@NotEmpty(message = "Mobile Number is required")
	private String mobileNumber;
	
	@NotEmpty(message = "Email id is required")
	private String emailId;	
	//@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	//@JsonManagedReference
	//@JsonIgnore
	@OneToMany(targetEntity = Project.class,cascade = CascadeType.ALL)
	@JoinColumn(name="student_Id",referencedColumnName = "id")
	private List<Project> projects;
	
	
}
