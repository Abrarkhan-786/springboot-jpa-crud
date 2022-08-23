package com.springbootmysql.crud.service;

import org.springframework.web.multipart.MultipartFile;

import com.springbootmysql.crud.bean.DataTableRequestBean;
import com.springbootmysql.crud.bean.ResponseBean;
import com.springbootmysql.crud.model.Employee;

public interface EmployeeService {
	public ResponseBean saveEmployee(Employee employee);
	public ResponseBean updateEmployee(Employee employee);
	public ResponseBean getAllEmployee();
	public ResponseBean findEmployeeById(Long id);
	public ResponseBean deleteEmployeeById(Long id);
	public ResponseBean findByDepartment(String department);
	public ResponseBean increaseSalaryByTenPercentageHavingDepartmentCricketElseFivePercentage();
	public Employee findEmployeeByEmail(String email);
	public ResponseBean downloadEmployeeReport();
	public ResponseBean uploadEmployeeExcel(MultipartFile file);
	public ResponseBean getAllEmployees(DataTableRequestBean dataTableRequestBean);

}
