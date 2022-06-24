package com.springbootmysql.crud.service;

import com.springbootmysql.crud.bean.ResponseBean;
import com.springbootmysql.crud.model.Employee;

public interface EmployeeService {
	public ResponseBean saveEmployee(Employee employee);
	public ResponseBean updateEmployee(Employee employee);
	public ResponseBean getAllEmployee();
	public ResponseBean findEmployeeById(Long id);
	public ResponseBean deleteEmployeeById(Long id);


}
