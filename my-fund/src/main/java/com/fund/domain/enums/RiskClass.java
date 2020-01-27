package com.fund.domain.enums;

public enum RiskClass {
	
	VERY_HIGH_RISK("VERY_HIGH_RISK"),
	HIGH_RISK("HIGH_RISK"),
	MEDIAN_RISK("MEDIAN_RISK"),
	LOW_RISK("LOW_RISK"),
	VERY_LOW_RISK("VERY_LOW_RISK"),
	;
	
	private String name;
	
	private RiskClass(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}

}
