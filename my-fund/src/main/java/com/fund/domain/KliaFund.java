package com.fund.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fund.domain.enums.FundType;
import com.fund.domain.enums.YOrN;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="klia_fund")
@Entity
public class KliaFund {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="klia_code") // 생명보험협회 펀드코드
	private String kliaCode;
	
	@Column(name="company") // 보험사
	private String company;
	
	@Column(name="setup_date") // 설정일
	private String setupDate;
	
	@OneToMany(mappedBy="kliaFund", fetch=FetchType.LAZY, cascade=CascadeType.ALL) // 설정일 기준 수익률리스트
	private List<KliaFundSetupProfit> kliaFundSetupProfits;
	
	@Column(name="fund_type") // 펀트 유형: 주식형, 주식혼합형, 채권형, 채권혼합형, 기타형 등
	@Enumerated(EnumType.STRING)
	private FundType fundType;
	
	@Lob
	@Column(name="plan")
	private String plan;
	
	@Lob
	@Column(name="elapse")
	private String elapse;
	
	@Column(name = "creation_datetime")
	@CreationTimestamp
	private Timestamp creationDatetime;

	@Column(name = "updated_datetime")
	@UpdateTimestamp
	private Timestamp updatedDatetime;

}
