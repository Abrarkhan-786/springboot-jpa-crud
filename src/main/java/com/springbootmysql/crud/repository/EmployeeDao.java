package com.springbootmysql.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootmysql.crud.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {


}
