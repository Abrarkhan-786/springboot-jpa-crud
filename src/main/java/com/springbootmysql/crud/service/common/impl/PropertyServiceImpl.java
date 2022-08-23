package com.springbootmysql.crud.service.common.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.springbootmysql.crud.service.common.PropertyService;

@Service
@Qualifier("propertyServiceImpl")
public class PropertyServiceImpl implements PropertyService {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public String getAppProperty(String code) {
		return env.getProperty(code) ;
	}

	@Override
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, Locale.ENGLISH) ;
	}

}
