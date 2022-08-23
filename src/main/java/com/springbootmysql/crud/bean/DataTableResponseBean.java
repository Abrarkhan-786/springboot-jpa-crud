package com.springbootmysql.crud.bean;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataTableResponseBean<T> {
	
	private List<T> data ;
    private int draw ;
    private int recordsFiltered ;
    private int recordsTotal ;
	
}
