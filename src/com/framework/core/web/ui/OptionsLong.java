package com.framework.core.web.ui;

public class OptionsLong {

	private long key;

	private String value;

	public OptionsLong() {

	}

	public OptionsLong(long key, String value) {
		this.key = key;
		this.value = value;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
