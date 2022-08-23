package com.springbootmysql.crud.bean;

import java.util.List;

import com.springbootmysql.crud.utility.dataTable.Column;
import com.springbootmysql.crud.utility.dataTable.Order;
import com.springbootmysql.crud.utility.dataTable.Search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataTableRequestBean {
	
private int draw;
private List<Column> columns;
private List<Order> order;
private int start;
private int length; 
private Search search; 
private String searchText;
private String sortableColumn;

}
