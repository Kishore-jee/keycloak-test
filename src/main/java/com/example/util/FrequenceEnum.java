package com.example.util;

public enum FrequenceEnum {
	Weekly("Weekly"), Bi_Weekly("Bi-Weekly");
	
	private String value;
	FrequenceEnum(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
