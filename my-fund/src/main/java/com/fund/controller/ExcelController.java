package com.fund.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fund.domain.Result;
import com.fund.service.ExcelService;

@RestController
@RequestMapping("/v1.0")
public class ExcelController {
	
	@Autowired
	ExcelService excelService;
	
	
	@GetMapping("/excel")
	public Result readExcel() {
		List<Map<String, String>> response = excelService.readXlsx("kofia_cost_standardDate.xlsx");
		return new Result(response, "OK", 200);
	}
	

}
