
package com.springbootmysql.crud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.contains;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.springbootmysql.crud.bean.ResponseBean;
import com.springbootmysql.crud.model.Employee;
import com.springbootmysql.crud.service.EmployeeService;

@SpringBootTest(classes = ApplicationStarter.class)
class SpringbootMysqlCrudApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	@Qualifier("employeeServiceImpl")
	private EmployeeService employeeService;
	
	@Disabled
	@Test
	 void findEmployeeById() {
		String actualName="dhoni";
		ResponseBean findEmployeeById = this.employeeService.findEmployeeById(3l);
		Employee employee= (Employee)findEmployeeById.getResponse();
		assertThat(actualName).isEqualTo(employee.getName());
		
	}
	
	@Disabled
	@Test
	void getAllEmployee() {
		List<Employee> allEmployee =(List<Employee>) this.employeeService.getAllEmployee().getResponse();
		//System.out.println(allEmployee);
		//assertThat(allEmployee).extracting(e->e.getName()).contains("siraj","dhoni");
		//assertThat(allEmployee).hasSize(8);
		//assertThat(allEmployee.get(3).getName()).isEqualTo("ponting");
		//assertThat(allEmployee).hasSize(8).extracting(Employee::getName).doesNotContain("abrar").contains("dhoni");
		assertThat(allEmployee.get(0)).matches(e->e.getSalary()>7000);
		
	}

}
