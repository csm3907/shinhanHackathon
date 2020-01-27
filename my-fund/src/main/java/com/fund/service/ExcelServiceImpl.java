package com.fund.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;

@Service("ExcelService")
public class ExcelServiceImpl implements ExcelService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);
	
	@Override
	public List<Map<String, String>> readXls(String filePath) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Map<String, String>> readXlsx(String filePath) {
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			// 1. 제목 추출
			XSSFRow titleRow = sheet.getRow(0);
			Map<Integer, String> titles = new HashMap<Integer, String>(); 
			for(int titleIndex = 0; titleIndex<titleRow.getPhysicalNumberOfCells(); titleIndex++) {
				titles.put(titleIndex, getValueByType(titleRow.getCell(titleIndex)));
			}
			
			int rows = sheet.getPhysicalNumberOfRows();
			
			for(int rowIndex=1; rowIndex<rows; rowIndex++) {
				Map<String, String> data = new HashMap<String, String>();
				XSSFRow row = sheet.getRow(rowIndex);
				
				if(row !=null) {
					int cells = row.getPhysicalNumberOfCells();
					for(int columnIndex = 0; columnIndex < cells; columnIndex++) {
						XSSFCell cell = row.getCell(columnIndex);
						logger.info("Colum title ==> " + titles.get((Integer) columnIndex) + ", value ==> " + getValueByType(cell));
						data.put(titles.get((Integer) columnIndex), getValueByType(cell));
					}
				}
				
				dataList.add(data);
			}
		} catch (FileNotFoundException e) {
			throw new CustomException(ExceptionCode.FILE_NOT_FOUND);
		} catch (IOException e) {
			throw new CustomException(ExceptionCode.FILE_IO_EXCEPTION);
		} catch (NullPointerException ne) {
			throw new CustomException(ExceptionCode.NOT_FOUND_DATA);
		}
		return dataList;
	}
	
	@Override
	public String getValueByType(XSSFCell cell) {
		String value = null;
		
		if (cell != null) {
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				value = cell.getStringCellValue().replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					value = new SimpleDateFormat("yyyy-MM-dd").format(date).replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
				} else {
					value = String.valueOf(cell.getNumericCellValue()).replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
				}
			}else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
				value = String.valueOf(cell.getBooleanCellValue()).replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
			}else if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
				value = "";
			}
		}

		return value;
	}
	
	@Override
	public void writeXls(String filePath) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void writeXlsx(String filePath) {
		// TODO Auto-generated method stub
		
	}

}
