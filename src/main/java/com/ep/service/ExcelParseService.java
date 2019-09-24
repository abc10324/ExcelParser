package com.ep.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelParseService {
	
	public Map<String,List<Map<String,Object>>> parseXLS(InputStream is) {
		try {
			return parse(new HSSFWorkbook(is));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public Map<String,List<Map<String,Object>>> parseXLSX(InputStream is) {
		try {
			return parse(new XSSFWorkbook(is));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	private Map<String,List<Map<String,Object>>> parse(Workbook workbook){
		Map<String,List<Map<String,Object>>> resultMap = new HashMap<>();
		
		for(Sheet sheet : workbook) {
			List<String> titleList = new ArrayList<String>();
			
			sheet.getRow(0).forEach((cell) -> {
				titleList.add(cell.toString());
			});
			
			List<Map<String,Object>> resultList = new ArrayList<>();
			
			for(int i=1 ; i<sheet.getPhysicalNumberOfRows(); i++) {
				
				List<String> colList = new ArrayList<String>();
				
				Row row = sheet.getRow(i);
				
				for(int j=0 ; j<titleList.size() ; j++) {
					Cell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
					if(cell != null) {
						colList.add(cell.toString());
					} else {
						colList.add("");
					}
				}
				
				Map<String,Object> rowMap = new HashMap<>();
				for(int j=0 ; j<titleList.size() ; j++) {
					rowMap.put(titleList.get(j), colList.get(j));
				}
				
				resultList.add(rowMap);
			}
			
			resultMap.put(sheet.getSheetName(), resultList);
		}
		
		return resultMap;
	}
	
//	public static void main(String[] args) {
//		for(int i=0 ; i<3 ; i++) {
//			System.out.printf("%5s",i);
//			System.out.printf("%10s\n","Empty");
//		}
//	}
	
}
