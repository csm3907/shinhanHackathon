package com.fund.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fund.domain.enums.YOrN;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="variable_insurance_fund")
@Entity
@IdClass(VariableInsuranceFundPK.class)
public class VariableInsuranceFund {
	
	@Id
	@ManyToOne
	@JoinColumn(name="variable_insurance_id")
	private VariableInsurance variableInsurance;
	
	@Id
	@ManyToOne
	@JoinColumn(name="fund_id")
	private Fund fund;
	
	@Enumerated(EnumType.STRING)
	@Column(name="use_yn")
	private YOrN useYn;
	
	@Column(name="base_date")
	private Timestamp baseDate;
	
	@Column(name = "creation_datetime")
	@CreationTimestamp
	private Timestamp creationDatetime;

	@Column(name = "updated_datetime")
	@UpdateTimestamp
	private Timestamp updatedDatetime;
}
