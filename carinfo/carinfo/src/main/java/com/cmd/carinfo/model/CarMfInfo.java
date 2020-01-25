package com.cmd.carinfo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarMfInfo {

	@JsonProperty("Count")
	private int Count;
	@JsonProperty("Results")
	private List<CarDetails> carDetails;
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public List<CarDetails> getCarDetails() {
		return carDetails;
	}
	public void setCarDetails(List<CarDetails> carDetails) {
		this.carDetails = carDetails;
	}
	
	
}
