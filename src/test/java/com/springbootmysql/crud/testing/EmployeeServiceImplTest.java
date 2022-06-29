package com.springbootmysql.crud.testing;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyDouble;

import org.junit.Assert;
import org.junit.Ignore;
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

}
