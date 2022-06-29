package com.springbootmysql.crud.testing;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springbootmysql.crud.ApplicationStarter;
import com.springbootmysql.crud.bean.EmployeeBean;
import com.springbootmysql.crud.repository.EmployeeDao;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { EmployeeDao.class })
@SpringBootTest(classes = ApplicationStarter.class)
public class EmployeeDaoTest {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Ignore
	@Test
	public void findByDepartment() {
		List<EmployeeBean> findByDepartment = employeeDao.findByDepartment("cricket");
		Assert.assertEquals(findByDepartment.size(), 7);
		
	}
	
	

}
