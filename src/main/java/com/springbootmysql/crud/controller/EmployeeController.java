package com.springbootmysql.crud.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springbootmysql.crud.bean.DataTableRequestBean;
import com.springbootmysql.crud.bean.ResponseBean;
import com.springbootmysql.crud.model.Employee;
import com.springbootmysql.crud.service.EmployeeService;
import com.springbootmysql.crud.utility.common.CommonUtility;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
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

	@RequestMapping(path="/findEmployeeById/{ID}")
	@ResponseBody
	public ResponseBean findEmployeeById(@PathVariable("ID") Long id) {
		return employeeService.findEmployeeById(id);
	}

	@RequestMapping(value="getAllEmployeeList" ,method = RequestMethod.GET)
	@ResponseBody
	public ResponseBean getAllEmployeeList()  {
		return employeeService.getAllEmployee();
	}
	
	@RequestMapping(value="getAllEmployees" ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean getAllEmployees(@RequestBody DataTableRequestBean dataTableRequestBean)  {
		return employeeService.getAllEmployees(dataTableRequestBean);
	}
	
	@RequestMapping(path="/deleteEmployeeById/{ID}")
	@ResponseBody
	public ResponseBean deleteEmployeeById(@PathVariable("ID") Long id) {
		return employeeService.deleteEmployeeById(id);
	}
	
	@RequestMapping(value="/findByDepartment")
    @ResponseBody
	public ResponseBean findByDepartment(@RequestParam("department") String department) {
		return employeeService.findByDepartment(department);
	}
	
	@RequestMapping(value="/increaseSalaryByTenPercentageHavingDepartmentCricketElseFivePercentage",method = RequestMethod.GET)
	public ResponseBean increaseSalaryByTenPercentageHavingDepartmentCricketElseFivePercentage() {
		return employeeService.increaseSalaryByTenPercentageHavingDepartmentCricketElseFivePercentage();
	}
	
	@RequestMapping(value="/downloadEmployeeReport")
	@ResponseBody
	public ResponseEntity<byte[]> downloadEmployeeReport(HttpServletRequest request, HttpServletResponse response) {
		//return employeeService.downloadEmployeeReport();
		
		 return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" +"employee.xlsx"
	                + "\"")
	                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
	                .body((byte[])employeeService.downloadEmployeeReport().getResponse());
	}
	
	
	@RequestMapping(value="/downloadEmployeeExcelReport")
	public void downloadEmployeeExcelReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ByteArrayInputStream byteInputStream =new ByteArrayInputStream((byte[])employeeService.downloadEmployeeReport().getResponse());
	          response.setContentType("application/octet-stream");
	          response.setHeader("Content-Disposition", "attachment; filename=employee_"+CommonUtility.converDateToString(new Date())+".xlsx");
	          IOUtils.copy(byteInputStream, response.getOutputStream());
     }
	
	@RequestMapping(value="uploadEmployeeExcel" ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean uploadEmployeeExcel(@RequestParam("file") MultipartFile file) {
		return employeeService.uploadEmployeeExcel(file);
	}
}
