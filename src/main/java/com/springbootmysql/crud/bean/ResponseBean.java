package com.springbootmysql.crud.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.springbootmysql.crud.constant.Status;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_EMPTY)
public class ResponseBean{
	private Status status;
	private String message;
	private Object response;
	

}
