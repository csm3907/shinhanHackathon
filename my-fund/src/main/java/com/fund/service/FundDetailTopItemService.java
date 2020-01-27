package com.fund.service;

import java.util.List;

import org.json.JSONArray;

import com.fund.domain.Fund;
import com.fund.domain.FundDetail;
import com.fund.domain.FundDetailTopItem;
import com.fund.dto.FundDetailTopItemDto;
import com.fund.dto.FundDetailTopItemDto.ResponseDto;

public interface FundDetailTopItemService {

	List<ResponseDto> getTopItems(FundDetail fundDetail);
	
	List<FundDetail> getFundDetailListByName(String name);
}
