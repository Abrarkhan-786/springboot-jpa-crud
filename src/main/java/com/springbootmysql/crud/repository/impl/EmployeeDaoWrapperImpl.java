package com.springbootmysql.crud.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.springbootmysql.crud.model.Employee;
import com.springbootmysql.crud.repository.common.impl.JpaEntityManagerImpl;

@Repository
public class EmployeeDaoWrapperImpl extends JpaEntityManagerImpl {
	
	public List<Employee> getAllEmployeeList() {
		StringBuffer sql=new StringBuffer();
		sql.append("select e from Employee e order by id desc");
		TypedQuery<Employee> query = entityManager.createQuery(sql.toString(),Employee.class);
		return query.getResultList();
	}
	

}
