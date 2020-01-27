package com.fund.service;

import com.fund.domain.enums.FundType;

public interface KofiaFundService {

	String getTotalExpenseRatioRankByFundType(String totalExpenseRatio, FundType fundType);
}
