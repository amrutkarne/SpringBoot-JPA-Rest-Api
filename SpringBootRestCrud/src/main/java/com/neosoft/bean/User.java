package com.neosoft.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Entity
@Table(name="userRegister")
@Data
@Where(clause="cvoid=1")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@NotEmpty(message = "First name is required")
	private String firstName;
	
	@NotEmpty(message = "Last name is required")
	private String lastName;
	
	@Past
	@NotNull(message = "Date of birth is required")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateOfBirth;
	
	@NotNull(message = "Joining date is required")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date joiningDate;
	
	@NotEmpty(message = "Pincode is required")
	@Size(max=6,message = "Pincode should be 6 digits")
	private String pincode;
	
	//@Pattern(regexp="(^$|[1]{1})",message = "Cvoid flag should be 1")
	@Min(message = "Cvoid flag should be 1 required", value = 1)
	private int cvoid;


}
