package com.example.tm_rentacar.enums;

public enum PaymentMethod {
	CREDIT_CARD("クレジットカード"),
	CASH("現金"),
	BANK_TRANSFER("銀行振り込み");
	
	private String name;
	private PaymentMethod(String name) {
		this.name = name;
	}
	public String getValue() {
		return name;
	}
}
