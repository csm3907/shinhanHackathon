package com.fund.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fund.domain.Fund;
import com.fund.domain.FundSetupProfit;

public interface FundSetupProfitRepository extends JpaRepository<FundSetupProfit, Serializable>{

	List<FundSetupProfit> findByFund(Fund fund);

}
