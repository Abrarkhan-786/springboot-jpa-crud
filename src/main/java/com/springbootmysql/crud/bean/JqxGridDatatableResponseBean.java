package com.springbootmysql.crud.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JqxGridDatatableResponseBean<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long totalRecords;
	private List<T> rows;
	
}
