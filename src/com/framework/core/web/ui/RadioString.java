package com.framework.core.web.ui;
/**
 * 封装option的键和值,可用于单选按钮
 * @author gaofeng
 *
 */
public class RadioString {

	private String key;

	private String value;

	public RadioString() {

	}

	public RadioString(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
