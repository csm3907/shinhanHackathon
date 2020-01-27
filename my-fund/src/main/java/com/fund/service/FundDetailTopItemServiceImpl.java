package com.fund.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fund.domain.FundDetail;
import com.fund.domain.FundDetailTopItem;
import com.fund.dto.FundDetailTopItemDto;
import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;
import com.fund.repository.FundDetailRepository;
import com.fund.repository.FundDetailTopItemRepository;

import ch.qos.logback.classic.Logger;

@Service("FundDetailTopItemService")
public class FundDetailTopItemServiceImpl implements FundDetailTopItemService {
	
	private final static Logger logger = (Logger) LoggerFactory.getLogger(FundDetailTopItemServiceImpl.class);

	@Autowired
	FundDetailService fundDetailService;
	
	@Autowired
	FundDetailTopItemRepository fundDetailTopItemRepository;
	
	@Override
	public List<FundDetailTopItemDto.ResponseDto> getTopItems(FundDetail fundDetail) {
		
		if(fundDetail.getId() == null) {
			throw new CustomException(ExceptionCode.NOT_FOUND_DATA);
		}
		List<FundDetailTopItemDto.ResponseDto> dtos = new ArrayList<FundDetailTopItemDto.ResponseDto>();
		List<FundDetailTopItem> topItems = fundDetailTopItemRepository.findByFundDetail(fundDetail);
		if(topItems == null || topItems.size() == 0) {
			return null;
		}
		
		for (FundDetailTopItem topItem : topItems) {
			FundDetailTopItemDto.ResponseDto dto = new FundDetailTopItemDto.ResponseDto(topItem);
			dtos.add(dto);
		}
		 
		return dtos;
	}
	
	@Override
	public List<FundDetail> getFundDetailListByName(String name) {
		logger.info("name ==> " + name);
		//List<FundDetailTopItem> fundDetailTopItems =  fundDetailTopItemRepository.findByName(name);
		//logger.info("id length ==> " + fundDetailTopItems.size());
		/*
		 * List<FundDetail> fundDetails = new ArrayList<FundDetail>();
		 * for(FundDetailTopItem fundDetailTopItem: fundDetailTopItems) {
		 * fundDetails.add(fundDetailTopItem.getFundDetail()); }
		 */
		List<FundDetail> fundDetails = new ArrayList<FundDetail>();
		List<Long> fundDetailIds = fundDetailTopItemRepository.getFundDetailListByName(name);
		for(Long id : fundDetailIds) {
			logger.info("id ==> " + id);
			FundDetail fundDetail = fundDetailService.findFundDetail(id);
			fundDetails.add(fundDetail);
		}
		
		
		
		return fundDetails;
	}
}
