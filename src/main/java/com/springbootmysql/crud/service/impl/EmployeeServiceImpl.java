package com.springbootmysql.crud.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springbootmysql.crud.bean.EmployeeBean;
import com.springbootmysql.crud.bean.ResponseBean;
import com.springbootmysql.crud.constant.Status;
import com.springbootmysql.crud.model.Employee;
import com.springbootmysql.crud.repository.EmployeeDao;
import com.springbootmysql.crud.service.EmployeeService;

@Service
@Qualifier("employeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public ResponseBean saveEmployee(Employee employee) {
		try {
			if (employee == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Data Is Null").build();
			}

			Employee employeeBean = employeeDao.save(employee);
			return ResponseBean.builder().status(Status.SUCCESS).message("Record Added Succesfully")
					.response(employeeBean).build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}

	}

	@Override
	public ResponseBean updateEmployee(Employee employee) {
		try {
			if (employee == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Data Is Null").build();
			}

			if (employee.getId() == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Id").build();
			}

			Optional<Employee> findById = employeeDao.findById(employee.getId());
			if (findById == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Valid Id").build();
			}

			Employee employeeBean = employeeDao.save(employee);
			return ResponseBean.builder().status(Status.SUCCESS).message("Record Added Succesfully")
					.response(employeeBean).build();

		} catch (Exception e) {
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}

	}

	@Override
	public ResponseBean getAllEmployee() {
		try {
			List<Employee> empolyeeList = employeeDao.findAll();
			if (empolyeeList != null  && empolyeeList.size()>0) {
				return ResponseBean.builder().status(Status.SUCCESS).response(empolyeeList).build();

			}

			return ResponseBean.builder().status(Status.FAIL).message("Record Not Found").build();

		} catch (Exception e) {

			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}
	}

	@Override
	public ResponseBean findEmployeeById(Long id) {
		try {
			if (id == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Id").build();
			}

			Optional<Employee> findById = employeeDao.findById(id);
			if (findById == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Valid Id").build();
			}

			return ResponseBean.builder().status(Status.SUCCESS).response(findById.get()).build();

		} catch (Exception e) {
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();

		}
	}

	@Override
	public ResponseBean deleteEmployeeById(Long id) {
		try {
			if (id == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Id").build();
			}

			Optional<Employee> findById = employeeDao.findById(id);
			if (findById == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Valid Id").build();
			}

			employeeDao.deleteById(findById.get().getId());

			return ResponseBean.builder().status(Status.SUCCESS).message("Record Deleted Sccessfully!").build();

		} catch (Exception e) {
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}
	}

	@Override
	public ResponseBean findByDepartment(String department) {
		try {
			List<EmployeeBean> empolyeeList = employeeDao.findByDepartment(department);
			if (empolyeeList != null  && empolyeeList.size()>0) {
				return ResponseBean.builder().status(Status.SUCCESS).response(empolyeeList).build();

			}

			return ResponseBean.builder().status(Status.FAIL).message("Record Not Found").build();

		} catch (Exception e) {
           e.printStackTrace();
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}
	}
	
	@Transactional
	   public ResponseBean  increaseSalaryByTenPercentageHavingDepartmentCricketElseFivePercentage() {
			try {
				List<Employee> empolyeeList = employeeDao.findAll();
				List<Employee> updatedList=new ArrayList<Employee>();
				if (empolyeeList != null  && empolyeeList.size()>0) {
				List<Employee> newEmployeeList = empolyeeList.stream().map((e)->{
						if(e.getDepartment().equalsIgnoreCase("cricket")) {
							e.setSalary(e.getSalary()*1.1);
						}else {
							e.setSalary(e.getSalary()*1.05);
						}
						return e;
					}).collect(Collectors.toList());
				
					for (Employee employee : newEmployeeList) {
						  Employee save = employeeDao.save(employee);
						  updatedList.add(save);
					}
					
					return ResponseBean.builder().status(Status.SUCCESS).response(updatedList).build();

				}

				return ResponseBean.builder().status(Status.FAIL).message("Record Not Found").build();

			} catch (Exception e) {

				return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
			}
		
		  
	  }

}
