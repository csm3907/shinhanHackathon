package com.fund.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fund.domain.FundDetail;
import com.fund.domain.FundDetailAssetPortfolio;

public interface FundDetailAssetPortfolioRepository extends JpaRepository<FundDetailAssetPortfolio, Serializable>{

	List<FundDetailAssetPortfolio> findByFundDetail(FundDetail fundDetail);

}
