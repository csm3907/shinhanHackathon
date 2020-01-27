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
import com.fund.dto.FundDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="fund")
@Entity
// 펀드 고유 정보, 기준일에 따른 변화  x
public class Fund {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="sc_code") // 신한생명 펀드코드
	private String scCode;
	
	@Column(name="klia_code") // 생명보험협회 펀드코드
	private String kliaCode;
	
	@Column(name="kofia_code") // 금융투자협회 펀드코드
	private String kofiaCode;
	
	@Column(name="company") // 보험사
	private String company;
	
	@Column(name="management_company") // 운용사
	private String managementCompany;
	
	@Column(name="setup_date") // 설정일
	private String setUpDate;
	
	@OneToMany(mappedBy="fund", fetch=FetchType.LAZY, cascade=CascadeType.ALL) // 설정일 기준 수익률리스트
	private List<FundSetupProfit> fundSetupProfits;
	
	@Column(name="risk_class") // 위험분류
	private String riskClass;
	
	@Column(name="fund_type") // 펀트 유형: 주식형, 주식혼합형, 채권형, 채권혼합형, 기타형 등
	@Enumerated(EnumType.STRING)
	private FundType fundType;
		
	@Enumerated(EnumType.STRING)
	@Column(name="use_yn")
	private YOrN useYn;
	
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
	
	public Fund(long i, String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	public FundDto.ResponseDto getResponseDto(){
		FundDto.ResponseDto responseDto = new FundDto.ResponseDto();
		
		responseDto.setId(this.getId());
		responseDto.setName(this.name);
		
		return responseDto;
	}


}
