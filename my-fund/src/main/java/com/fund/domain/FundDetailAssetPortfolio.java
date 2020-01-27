package com.fund.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fund.domain.enums.AssetType;
import com.fund.domain.enums.YOrN;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="fund_detail_asset_portfolio")
@Entity
public class FundDetailAssetPortfolio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="fund_detail_id", referencedColumnName="id", nullable=false)
	private FundDetail fundDetail;
	
	@Column(name="name")
	@Enumerated(EnumType.STRING)
	private AssetType assetType;
	
	@Column(name="ratio")
	private String ratio;
	
	@Column(name = "creation_datetime")
	@CreationTimestamp
	private Timestamp creationDatetime;

	@Column(name = "updated_datetime")
	@UpdateTimestamp
	private Timestamp updatedDatetime;
}
