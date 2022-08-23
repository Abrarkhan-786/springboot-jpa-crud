package com.springbootmysql.crud.utility.dataTable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Search {
	private String value;
	private boolean regex;

}
