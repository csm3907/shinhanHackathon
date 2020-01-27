package com.fund.domain.enums;

public enum YOrN {
	Y("Y"),
	N("N");
	
	private String name;
	
	private YOrN(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}
}
