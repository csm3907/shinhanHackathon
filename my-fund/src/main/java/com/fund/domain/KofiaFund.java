package com.fund.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fund.domain.enums.FundType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="kofia_fund")
public class KofiaFund {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="kofia_code") // 금융투자협회 펀드코드
	private String kofiaCode;
	
	@Column(name="setup_date") // 설정일
	private String setupDate;
	
	@Column(name="standard_date") // 기준일
	private String standardDate;
	
	@Column(name="name")
	private String name;
	
	@Column(name="management_company") // 운용사
	private String managementCompany;
	
	@Column(name="fund_type") // 펀트 유형: 주식형, 주식혼합형, 채권형, 채권혼합형, 기타형 등
	@Enumerated(EnumType.STRING)
	private FundType fundType;
	
	@Column(name="total_expense_ratio") // 총보수비용비율, TER
	private String totalExpenseRatio;
	
	@Column(name="trading_fee_ratio") // 매매중개수수료비율(연환산)
	private String tradingFeeRatio;
}
