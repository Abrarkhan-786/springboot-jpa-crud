package com.springbootmysql.crud.utility.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.springbootmysql.crud.constant.GlobalConstant;
import com.springbootmysql.crud.service.common.PropertyService;

public class CommonUtility {
	
	@Autowired
	@Qualifier("propertyServiceImpl")
	PropertyService propertyService;

    public static final DateFormat dateFormate= new SimpleDateFormat(GlobalConstant.DATETIME_FORMAT);
    
      public static String converDateToString (Date date) {
    	  try {
    		  if(date!=null) {
    			 return  dateFormate.format(date);
    		  }
    		  
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	  }
    	  
    	  return null;
    	
    }
      public static boolean isNumeric(String strNum) {
		    if (strNum == null) {
		        return false;
		    }
		    try {
		        double d = Double.parseDouble(strNum);
		    } catch (NumberFormatException nfe) {
		        return false;
		    }
		    return true;
		}

}
