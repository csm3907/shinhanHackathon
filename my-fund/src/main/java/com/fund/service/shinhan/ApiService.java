package com.fund.service.shinhan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fund.dto.shinhan.ContractVariablesDto;
import com.fund.dto.shinhan.ContractVariablesDto.RequestDto;
import com.fund.dto.shinhan.ContractVariablesDto.ResponseDto;
import com.fund.dto.shinhan.StockChartDto;

public interface ApiService {
	
	ContractVariablesDto.ResponseDto sendRequestForContractVariables(String rdreNo);

	com.fund.dto.shinhan.StockChartDto.ResponseDto sendRequestForStockChart(String code, String qryStrtYmd,
			String qryEndYmd);

	com.fund.dto.shinhan.StockInfoNewslistDto.ResponseDto sendRequestForStockNewsList();

	com.fund.dto.shinhan.StockInfoNewsdtlDto.ResponseDto sendRequestForStockNewsInfo(String date, String seq, String newsCls);

	com.fund.dto.shinhan.StockConcstbdSearchDto.ResponseDto sendRequestForStockConcstbdSearch();
	
	

}
