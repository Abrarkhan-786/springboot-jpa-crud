package com.springbootmysql.crud.utility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Demo {

	public static void main(String[] args) {
String sDate="2022-01-11T18:30:00.000Z";
        
        String eDate="2023-05-11T18:30:00.000Z";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate=null;
		Date startDate=null;
		try {
		   
			endDate = formatter.parse(eDate);
			 System.out.println(endDate);
			startDate = formatter.parse(sDate);
			 System.out.println(startDate);
			long timeDiff=endDate.getTime() - startDate.getTime();
			long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
			System.out.println(daysDiff);
			float c= (float) (daysDiff/365.0);
			 DecimalFormat f = new DecimalFormat("##.00");
		     System.out.println(f.format(c));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

	

}
