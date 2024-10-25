package com.example.tm_rentacar.enums;

public enum CarType {
    SEDAN("セダン"),
    SPORT("スポーツカー"),
    LIGHTVEHICLE("軽自動車"),
    ONEBOX("ワンボックス"),
    COMPACT("コンパクトカー"),
    STATIONWAGON("ステーションワゴン"),
    SUV("SUV"),
    OPENCAR("オープンカー"),
    COUPE("クーペ"),
    EV("電気自動車"),
    HYBRID("ハイブリット車");
	
	private String name;
	
	private CarType(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}