package com.fund.domain.enums;

public enum AssetType {
	
	STOCK("STOCK"), // 주식
	BOND("BOND"), // 채권
	BENEFICIARY_CERTIFICATE("BENEFICIARY_CERTIFICATE"), // 수익증권
	LIQUIDITY("LIQUIDITY"), // 유동성 자산
	ETC("ETC") // 기타
	;
	
	private String name;
	
	private AssetType(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}

}
