package com.framework.core.web.ui;
/**
 * 封装option的键和值,可用于单选按钮
 * @author gaofeng
 *
 */
public class RadioInt {

	private int key;

	private String value;

	public RadioInt() {

	}

	public RadioInt(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
