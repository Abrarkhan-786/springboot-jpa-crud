package com.springbootmysql.crud.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springbootmysql.crud.bean.DataTableRequestBean;
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
	
	
	@Query(
			  "SELECT e "
			+ "FROM  Employee e  "
			+ "WHERE "
			+ "CONCAT(e.id, '') LIKE %:query% "
			+ "OR "
			+ "e.name LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.email LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.department LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "CONCAT(e.salary, '') LIKE %:query% "
			+ "GROUP BY :column"
			
			)
	   public Page<Employee> getAllEmployees(@Param("query") String query,@Param("column") String column, Pageable page);
	
	  
	
	
	
}
