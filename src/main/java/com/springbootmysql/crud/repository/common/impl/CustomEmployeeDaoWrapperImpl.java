package com.springbootmysql.crud.repository.common.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.springbootmysql.crud.model.Employee;
import com.springbootmysql.crud.repository.common.CustomEmployeeDao;

@Repository
public class CustomEmployeeDaoWrapperImpl implements CustomEmployeeDao {
	@PersistenceContext
    protected EntityManager entityManager;
	
	public List<Employee> getAllEmployeeList() {
		StringBuffer sql=new StringBuffer();
		sql.append("select e from Employee e order by id desc");
		TypedQuery<Employee> query = entityManager.createQuery(sql.toString(),Employee.class);
		return query.getResultList();
	}
	
//	public List<Employee> getAllEmployees() {
//		
//	
//		
//		TypedQuery<Employee> query = entityManager.createQuery(sql.toString(),Employee.class);
//		return query.getResultList();
//	}
//	

}
