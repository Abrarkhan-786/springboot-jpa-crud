package com.springbootmysql.crud.bean;

import com.springbootmysql.crud.constant.Status;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseBean {
	private Status status;
	private String message;
	private Object response;
	

}
