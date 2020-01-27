package com.fund.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fund.domain.FundDetail;
import com.fund.domain.FundDetailStandardProfit;

public interface FundDetailStandardProfitRepository extends JpaRepository<FundDetailStandardProfit, Serializable>{

	FundDetailStandardProfit findByFundDetail(FundDetail fundDetail);

}
