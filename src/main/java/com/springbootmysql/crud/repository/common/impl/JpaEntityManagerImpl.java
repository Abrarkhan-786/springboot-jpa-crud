package com.springbootmysql.crud.repository.common.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.springbootmysql.crud.repository.common.JpaEntityManger;

public class JpaEntityManagerImpl implements JpaEntityManger {
	
	@PersistenceContext
    protected EntityManager entityManager;
}
