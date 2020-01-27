package com.fund.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="fund_detail_top_item")
@Data
public class FundDetailTopItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="fund_detail_id", referencedColumnName="id", nullable=false)
	private FundDetail fundDetail;
	
	@Column(name="name")
	private String name;
	
	@Column(name="rate")
	private String rate;
}
