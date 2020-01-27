package com.fund.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="fund_detail")
@NoArgsConstructor
@AllArgsConstructor
// 기준일에 따른 펀드 정보
public class FundDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fund_id", referencedColumnName="id")
	private Fund fund;
	
	@Column(name="standard_date") // 기준일
	private String standardDate;
	
	@Column(name="total_expense_ratio") // 총보수비용비율, TER
	private String totalExpenseRatio;
	
	@Column(name="total_expense_ratio_rank") // 총보수비용비율, TER
	private String totalExpenseRatioRank;
	
	@Column(name="standard_price") // 기준가격
	private String standardPrice;
	
	@Column(name="net_assets") // 순자산액
	private String netAssets;
	
	@Column(name="turnover_ratio") // 매매회전율
	private String turnoverRatio;
	
	@Column(name="turnover_ratio_rank") // 매매회전율 순위 
	private String turnoverRatioRank;
	
	@Column(name="trading_fee_ratio") // 매매중개수수료비율(연환산)
	private String tradingFeeRatio;
	
	@Column(name="trading_fee_ratio_rank") // 매매중개수수료비율(연환산) 순위
	private String tradingFeeRatioRank;
	
	@Column(name="total_fund_count") // 펀드 유형별 전체 펀드 개수
	private String totalFundCount;
	
	@Column(name="top_items_rate") // 상위종목이 차지하는 비율
	private String TopItemsRate;
	
	@Column(name="manager") // 펀드매니저
	private String manager;
	
	@Column(name="manager_date") // 펀드매니저 운용일
	private String managerDate;
	
	@Column(name = "creation_datetime")
	@CreationTimestamp
	private Timestamp creationDatetime;

	@Column(name = "updated_datetime")
	@UpdateTimestamp
	private Timestamp updatedDatetime;

}
