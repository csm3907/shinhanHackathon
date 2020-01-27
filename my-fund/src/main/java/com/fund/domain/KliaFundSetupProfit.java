package com.fund.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="klia_fund_setup_profit")
public class KliaFundSetupProfit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="klia_fund_id", referencedColumnName="id")
	private KliaFund kliaFund;
		
	@Column(name="date")
	private String date;
	
	@Column(name="profit_ratio")
	private String profitRatio;

}
