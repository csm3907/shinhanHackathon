package com.fund.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fund.domain.KofiaFund;
import com.fund.domain.enums.FundType;

public interface KofiaFundRepository extends JpaRepository<KofiaFund, Serializable>{
	
	@Query(value="select count(*), fund_type from kofia_fund where fund_type = :fundType group by fund_type;", nativeQuery = true)
	String getCountByFundType(@Param("fundType") String fundType);
	
	@Query(value="select * from kofia_fund where fund_type = :fundType order by total_expense_ratio asc;", nativeQuery = true)
	List<KofiaFund> findByFundTypeOrderByTotalExpenseRatio(@Param("fundType") String fundType);
	
	@Query(value="select * from kofia_fund where fund_type = :fundType order by trading_fee_ratio asc;", nativeQuery = true)
	List<KofiaFund> findByFundTypeOrderByTradingFeeRatio(@Param("fundType") String fundType);

}
