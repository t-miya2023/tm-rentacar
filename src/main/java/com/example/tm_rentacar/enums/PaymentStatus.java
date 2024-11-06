package com.example.tm_rentacar.enums;

public enum PaymentStatus {
	PENDING("保留中"),
	PAID("完了"),
	FAILD("失敗"),
	REFUNDED("返金");
	
	private String name;
	
	private PaymentStatus(String name) {
		this.name = name;
	}
	public String getValue() {
		return name;
	}
}
