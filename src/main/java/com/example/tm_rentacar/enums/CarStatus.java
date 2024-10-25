package com.example.tm_rentacar.enums;

public enum CarStatus {
	AVAILABLE("利用化"),
	RENTED("貸出中"),
	UNDER_MAINTENANCE("点検中");
	
	private String name;
	private CarStatus(String name) {
		this.name = name;
	}
	public String getValue() {
		return name;
	}
}
