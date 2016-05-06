package com.itcast.test;

public class Address {

	private Integer aid;// 主键
	private String province;// 省
	private String city;// 城市
	private String street;// 街道

	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getAid() {
		return aid;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Address [aid=" + aid + ", province=" + province + ", city="
				+ city + ", street=" + street + "]";
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(Integer aid, String province, String city, String street) {
		super();
		this.aid = aid;
		this.province = province;
		this.city = city;
		this.street = street;
	}

}
