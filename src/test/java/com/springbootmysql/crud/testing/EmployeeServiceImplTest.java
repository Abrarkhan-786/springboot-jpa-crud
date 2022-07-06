package com.springbootmysql.crud.testing;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyDouble;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.springbootmysql.crud.model.Employee;
import com.springbootmysql.crud.repository.EmployeeDao;
import com.springbootmysql.crud.service.impl.EmployeeServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EmployeeServiceImpl.class})
public class EmployeeServiceImplTest {
	
	
    @Ignore
	@Test
	public void findEmployeeByEmailTest() {
		EmployeeDao mockEmployeeDao=PowerMockito.mock(EmployeeDao.class);

		EmployeeServiceImpl employeeServiceImpl=new EmployeeServiceImpl(mockEmployeeDao);
		Employee e=new Employee();
		e.setDepartment("cricket");
		e.setEmail("pollard@gmail.com");
		e.setName("pollard");
		e.setSalary(1100000);
		e.setSenior(false);
		PowerMockito.when(mockEmployeeDao.findEmployeeByEmail("pollard@gmail.com")).thenReturn(e);
		
		Employee findEmployeeByEmail = employeeServiceImpl.findEmployeeByEmail("pollard@gmail.com");
		assertNotNull(findEmployeeByEmail);
		Assert.assertEquals(e.getSalary(), findEmployeeByEmail.getSalary(),anyDouble());
	}
    
//    @Test
//    public void saveEmplyeeTest() {
//		EmployeeDao mockEmployeeDao=PowerMockito.mock(EmployeeDao.class);
//		EmployeeServiceImpl employeeServiceImpl=new EmployeeServiceImpl(mockEmployeeDao);
//		Employee emp=new Employee();
//		emp.setDepartment("java develper");
//		emp.setEmail("sunny@gmail.com");
//		emp.setName("sunny");
//		emp.setSalary(19000);
//		emp.setSenior (false);
//		
//		
//		PowerMockito.when(mockEmployeeDao.save(emp)).thenReturn(emp);
//		Employee employeeBean =(Employee) employeeServiceImpl.saveEmployee(emp).getResponse();
//		  assertNotNull(employeeBean);
//		
//		
//    }
    
//    @Disabled
//    @Ignore
//    @Test
//    public void updateEmplyeeTest() {
//		EmployeeDao mockEmployeeDao=PowerMockito.mock(EmployeeDao.class);
//		EmployeeServiceImpl employeeServiceImpl=new EmployeeServiceImpl(mockEmployeeDao);
//		Employee emp=new Employee();
//		emp.setId(1l);
//		emp.setDepartment("cricket");
//		emp.setEmail("william@gmail.com");
//		emp.setName("william");
//		emp.setSalary(8000);
//		emp.setSenior (true);
//		
//		PowerMockito.when(mockEmployeeDao.save(emp)).thenReturn(emp);
//		Employee employeeBean =(Employee) employeeServiceImpl.updateEmployee(emp).getResponse();
//		  assertNotNull(employeeBean);
//		  Assert.assertEquals(emp.getEmail(),employeeBean.getEmail());
//		
//    }
    
    @Test
    public void findByIdEmplyeeTest() {
		EmployeeDao mockEmployeeDao=PowerMockito.mock(EmployeeDao.class);
		EmployeeServiceImpl employeeServiceImpl=new EmployeeServiceImpl(mockEmployeeDao);
		Employee emp=new Employee();
		emp.setId(1);
		emp.setDepartment("cricket");
		emp.setEmail("william@gmail.com");
		emp.setName("william");
		emp.setSalary(8000);
		emp.setSenior (true);	
		PowerMockito.when(mockEmployeeDao.findById(1l)).thenReturn(Optional.of(emp));
		Employee employeeBean =(Employee) employeeServiceImpl.findEmployeeById(1l).getResponse();
		  assertNotNull(employeeBean);
		  Assert.assertEquals(emp.getId(),employeeBean.getId());
		  Assert.assertEquals(emp.getId(),employeeBean.getId());
    }

}
