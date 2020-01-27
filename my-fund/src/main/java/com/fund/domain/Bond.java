package com.fund.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="bond")
@Entity
public class Bond {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="use_yn")
	private YOrN useYn;
	
	@Column(name = "creation_datetime")
	@CreationTimestamp
	private Timestamp creationDatetime;

	@Column(name = "updated_datetime")
	@UpdateTimestamp
	private Timestamp updatedDatetime;

}
