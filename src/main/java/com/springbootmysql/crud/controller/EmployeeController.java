package com.springbootmysql.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootmysql.crud.bean.ResponseBean;
import com.springbootmysql.crud.model.Employee;
import com.springbootmysql.crud.service.EmployeeService;

@CrossOrigin(allowCredentials="false")
@RestController
@RequestMapping(value="/employee")
public class EmployeeController {

	@Autowired
	@Qualifier("employeeServiceImpl")
	EmployeeService employeeService;
	
	@RequestMapping(value="saveEmployee" ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	@RequestMapping(value="/updateEmployee" ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	@RequestMapping(value="/findEmployeeById/{ID}")
	@ResponseBody
	public ResponseBean findEmployeeById(@PathVariable("ID") Long id) {
		return employeeService.findEmployeeById(id);
	}

	@RequestMapping(path="/getAllEmployeeList")
	@ResponseBody
	public ResponseBean getAllEmployeeList() {
		return employeeService.getAllEmployee();
	}
	
	@RequestMapping(path="/deleteEmployeeById/{ID}")
	@ResponseBody
	public ResponseBean deleteEmployeeById(@PathVariable("ID") Long id) {
		return employeeService.deleteEmployeeById(id);
	}
	
	@RequestMapping(path="/findByDepartment")
//	@ResponseBody
	public ResponseBean findByDepartment(@RequestParam String department) {
		return employeeService.findByDepartment(department);
	}

}
