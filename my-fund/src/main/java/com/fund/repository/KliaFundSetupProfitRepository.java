package com.fund.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fund.domain.KliaFund;
import com.fund.domain.KliaFundSetupProfit;

public interface KliaFundSetupProfitRepository extends JpaRepository<KliaFundSetupProfit, Serializable> {

	List<KliaFundSetupProfit> findByKliaFund(KliaFund kliaFund);

}
