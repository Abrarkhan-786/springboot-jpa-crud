package com.springbootmysql.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springbootmysql.crud.bean.EmployeeBean;
import com.springbootmysql.crud.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

	@Query("Select" 
			+" new com.springbootmysql.crud.bean.EmployeeBean (e.id, e.email,e.name) "
			+ "from Employee e  "
			+ "where e.department=?1 "
			+ "order by e.id asc")
	 List<EmployeeBean> findByDepartment(String department);
	
	@Query("Select" 
			+" e "
			+ "from Employee e  "
			+ "where e.email=:email")
	public Employee findEmployeeByEmail(@Param("email")  String email);
	
	
}
