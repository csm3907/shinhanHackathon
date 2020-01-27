package com.fund.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="fund_detail_standard_profit")
@Entity
public class FundDetailStandardProfit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@JoinColumn(name="fund_detail_id", referencedColumnName="id", nullable=false)
	private FundDetail fundDetail;
	
	@Column(name="three_month")
	private String threeMonths;
	
	@Column(name="six_month")
	private String sixMonths;
	
	@Column(name="nine_month")
	private String nineMonths;
	
	@Column(name="one_year")
	private String oneYear;
	
	@Column(name="two_years")
	private String twoYears;
	
	@Column(name="three_years")
	private String threeYears;
	
	@Column(name="five_years")
	private String fiveYears;
	
	@Column(name = "creation_datetime")
	@CreationTimestamp
	private Timestamp creationDatetime;

	@Column(name = "updated_datetime")
	@UpdateTimestamp
	private Timestamp updatedDatetime;

}
