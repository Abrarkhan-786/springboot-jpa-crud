package com.springbootmysql.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee")
@Data
//@Builder
@NoArgsConstructor
public class Employee implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="employee_name")
	@NotNull
	private String  name;
	
	@Column(name="employee_email")
	@Email(message = "Email is not valid",regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	private String  email;
	
	@Column(name="employee_salary")
	private double salary;
	
	@Column(name="employee_department")
	@NotNull
	private String  department;
	
	private boolean isSenior;

}
