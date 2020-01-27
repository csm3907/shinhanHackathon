package com.fund.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fund.domain.FundDetail;
import com.fund.domain.FundDetailTopItem;

public interface FundDetailTopItemRepository extends JpaRepository<FundDetailTopItem, Serializable> {

	List<FundDetailTopItem> findByFundDetail(FundDetail fundDetail);

	@Query(value="select DISTINCT  fund_detail_id from fund_detail_top_item where name like %:name%", nativeQuery=true)
	List<Long> getFundDetailListByName(@Param("name") String name);
	
	List<FundDetailTopItem> findByName(String name);

}
