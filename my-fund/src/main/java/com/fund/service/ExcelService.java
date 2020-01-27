package com.fund.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;

public interface ExcelService {
	
	List<Map<String, String>> readXlsx(String filePath);
	
	void writeXlsx(String filePath);
	
	List<Map<String, String>> readXls(String filePath);
	
	void writeXls(String filePath);
	
	String getValueByType(XSSFCell cell);

}
