package com.bingjie.account.provider;

public class PieChartDataItem {
	private String title;
	private float value;
	private int color;
	private float degrees;

	public PieChartDataItem(String title, float value, int color) {
		this.color = color;
		this.title = title;
		this.value = value;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public void setDegrees(float degrees) {
		this.degrees = degrees;
	}

	public float getDegrees() {
		return this.degrees;
	}

}
