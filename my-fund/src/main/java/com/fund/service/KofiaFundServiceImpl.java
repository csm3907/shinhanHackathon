package com.fund.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fund.domain.KofiaFund;
import com.fund.domain.enums.FundType;
import com.fund.repository.KofiaFundRepository;

@Service("KofiaFundService")
public class KofiaFundServiceImpl implements KofiaFundService {
	
	@Autowired
	KofiaFundRepository kofiaFundRepository;
	
	
	@Override
	public String getTotalExpenseRatioRankByFundType(String totalExpenseRatio, FundType fundType) {
		
		
		return null;
	}
}
