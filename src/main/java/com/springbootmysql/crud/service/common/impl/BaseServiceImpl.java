package com.springbootmysql.crud.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springbootmysql.crud.service.common.BaseService;
import com.springbootmysql.crud.service.common.PropertyService;

@Service
@Qualifier("baseServiceImpl")
public class BaseServiceImpl implements BaseService{
	
	@Autowired
	@Qualifier("propertyServiceImpl")
	protected PropertyService propertyService;

}
