package com.springbootmysql.crud.utility.dataTable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Column {
	
private String data;
private String name;
private boolean searchable;
private boolean orderable; 
private Search search;

}
