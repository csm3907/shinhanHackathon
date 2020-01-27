package com.fund.domain.kakao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fund.domain.Fund;
import com.fund.domain.FundSetupProfit;
import com.fund.domain.enums.FundType;
import com.fund.domain.enums.YOrN;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="kakao_token")
@Entity
public class KakaoToken {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="code")
	private String code;
	
	@Lob
	@Column(name="refresh_token")
	private String refreshToken;

	@Lob
	@Column(name="access_tokne")
	private String accessToken;
	
	@Column(name="expires_in")
	private String expiresIn;
	
	@Column(name="token_type")
	private String tokenType;
	
	@Column(name="scope")
	private String scope;
	
	@Column(name="refresh_token_expires_in")
	private String refreshTokenExpiresIn;
	
	@Column(name = "creation_datetime")
	@CreationTimestamp
	private Timestamp creationDatetime;

	@Column(name = "updated_datetime")
	@UpdateTimestamp
	private Timestamp updatedDatetime;
}
