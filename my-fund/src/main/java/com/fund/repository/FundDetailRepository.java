package com.fund.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fund.domain.Fund;
import com.fund.domain.FundDetail;

public interface FundDetailRepository extends JpaRepository<FundDetail, Serializable> {

	FundDetail findByFund(Fund fund);

}
