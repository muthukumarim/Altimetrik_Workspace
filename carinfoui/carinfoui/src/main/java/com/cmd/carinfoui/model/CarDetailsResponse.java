package com.cmd.carinfoui.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarDetailsResponse {

	@JsonProperty("carDetails")
	private List<CarDetails> carDetails;

	public List<CarDetails> getCarDetails() {
		return carDetails;
	}

	public void setCarDetails(List<CarDetails> carDetails) {
		this.carDetails = carDetails;
	}
	
	
}