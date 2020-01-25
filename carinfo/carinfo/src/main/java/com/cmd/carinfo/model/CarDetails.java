package com.cmd.carinfo.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CarDetails {

	@JsonProperty("Mfr_ID")
	private int mfID;
	@JsonProperty("Mfr_Name")
	private String mfName;
	@JsonProperty("City")
	private String city;
	@JsonProperty("StateProvince")
	private String state;
	@JsonProperty("Country")
	private String country;
	
	public int getMfID() {
		return mfID;
	}
	public void setMfID(int mfID) {
		this.mfID = mfID;
	}
	public String getMfName() {
		return mfName;
	}
	public void setMfName(String mfName) {
		this.mfName = mfName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
