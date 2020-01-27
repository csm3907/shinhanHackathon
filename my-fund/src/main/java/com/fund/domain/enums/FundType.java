package com.fund.domain.enums;

public enum FundType {
	
	STOCK("STOCK"), // 주식형
	MIXED_STOCK("MIXED_STOCK"), // 주식혼합형
	BOND("BOND"), // 채권형
	MIXED_BOND("MIXED_BOND"), // 채권혼합형
	REINDIRECTNESS("REINDIRECTNESS"), // 재간접형
	REAL_ESTATE("REAL_ESTATE"), // 부동산형
	ETC("ETC") // 기타형
	;
	
	private String name;
	
	private FundType(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}

}
