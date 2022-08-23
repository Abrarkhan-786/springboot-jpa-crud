package com.springbootmysql.crud.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springbootmysql.crud.utility.jqx.FilterGroupsBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JqxGridDatatableRequestBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Paging
	private int pagenum;
	private int pagesize;
	private int recordendindex;
	private int recordstartindex;
	private int totalRecords;
	
	//sorting
	private String sortdatafield;
	private String sortorder;
		
	//Filter
	private List<FilterGroupsBean> filterGroups;
	
	//Grouping
	private int groupscount;
	//TODO: Need to implement grouping 
	
	//Extra fields
	private Map<String, Object> extraParam;
	
	
}
