package com.springbootmysql.crud.utility.common;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelUtil {

	public static SXSSFWorkbook dataToExcellSheet(String[] column, List<String[]> data, String filName)
			throws IOException {
		SXSSFWorkbook xlsWorkbook = new SXSSFWorkbook();
		// Create a blank sheet
		SXSSFSheet xlsSheet = xlsWorkbook.createSheet(filName);
		xlsSheet.setDefaultColumnWidth(20);
        
		SXSSFRow tileRow = (SXSSFRow)xlsSheet.createRow(0);

		// Create header CellStyle
		Font headerFont = xlsWorkbook.createFont();
		headerFont.setColor(IndexedColors.WHITE.index);
		headerFont.setBold(true);
		CellStyle headerCellStyle = xlsWorkbook.createCellStyle();
		headerCellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
		
		// Create data CellStyle
				CellStyle dataCellStyle = xlsWorkbook.createCellStyle();
				dataCellStyle.setAlignment(HorizontalAlignment.CENTER);

		
		// Creating header
		for (int col = 0; col < column.length; col++) {
			 tileRow.createCell(col);
			 tileRow.getCell(col).setCellValue(column[col]);
			 tileRow.getCell(col).setCellStyle(headerCellStyle);
		}

		// creating data row
		for (int i = 0; i < data.size(); i++) {
			SXSSFRow dataRow = (SXSSFRow)xlsSheet.createRow(i+1);
			for (int j = 0; j < data.get(i).length; j++) {
				dataRow.createCell(j).setCellValue(
						(data.get(i)[j] != null && !data.get(i)[j].toString().equals("null")) ? data.get(i)[j] : "");
				dataRow.getCell(j).setCellStyle(dataCellStyle);
			}
		}

		// Making size of column auto resize to fit with data
//		for (int i = 0; i < column.length; i++) {
//			xlsSheet.trackAllColumnsForAutoSizing();
//			xlsSheet.autoSizeColumn(i);
//			}

		return xlsWorkbook;

	}
	
	
	
	

}
