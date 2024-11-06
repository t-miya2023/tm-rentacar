package com.example.tm_rentacar.enums;

public enum ReservationStatus {
	PENDING("保留中"),
	CONFIRMED("確定"),
	CANCELLED("キャンセル"),
	COMPLETE("完了");
	
	private String name;
	private ReservationStatus(String name) {
		this.name = name;
	}
	public String getValue() {
		return name;
	}
}
