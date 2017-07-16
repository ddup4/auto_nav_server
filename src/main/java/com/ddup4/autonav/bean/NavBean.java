package com.ddup4.autonav.bean;

public class NavBean {

	private int id;
	private String phone;
	private String longtitude;
	private String latitude;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "NavBean [id=" + id + ", phone=" + phone + ", longtitude=" + longtitude + ", latitude=" + latitude + "]";
	}

}
