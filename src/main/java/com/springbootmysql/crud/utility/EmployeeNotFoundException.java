package com.springbootmysql.crud.utility;

public class EmployeeNotFoundException extends RuntimeException{
	public EmployeeNotFoundException(String s) {
		super(s);
	}
}
