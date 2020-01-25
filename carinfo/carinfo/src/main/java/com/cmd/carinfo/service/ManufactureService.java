package com.cmd.carinfo.service;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.cmd.carinfo.model.CarDetails;
import com.cmd.carinfo.model.CarDetailsResponse;
import com.cmd.carinfo.model.CarMfInfo;
import com.cmd.carinfo.model.ResponseInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

public class ManufactureService implements Manufacture {

	
	
	@Override
	public Response healthCheck(String message) {
		// TODO Auto-generated method stub
		return Response.ok().entity("Hello " + message).build();
	}

	@Override
	public Response getManufactureInfo(String carName) {
		// TODO Auto-generated method stub
		String restUrl = "https://vpic.nhtsa.dot.gov/api/vehicles/GetManufacturerDetails/<mfr>?format=json";
		ObjectMapper mapper = new ObjectMapper();
		ResponseInfo  responseInfo =new ResponseInfo();
		try {
			if(carName !=null && carName.length() > 0 ) {
			String url = restUrl.replaceFirst("<mfr>", carName);
			RestTemplate restTemplate =new RestTemplate();
			 String result = restTemplate.getForObject(url, String.class);
			 mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			 mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			 mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			 CarMfInfo carMfInfo = mapper.readValue(result, CarMfInfo.class);
			 if(carMfInfo.getCount()>0) {
			 List<CarDetails> details = carMfInfo.getCarDetails();
			 CarDetailsResponse carDetailsResponse =new CarDetailsResponse();
			 carDetailsResponse.setCarDetails(details);
			 return Response.status(201).entity(carDetailsResponse).build();
			 }else
			 {
				 responseInfo.setCode(400);
				 responseInfo.setMessage("Sorry not able any matching Car Manufacture Info ");
			return Response.status(201).entity(responseInfo).build();
			 }
			}
			else {
				 responseInfo.setCode(400);
				 responseInfo.setMessage("Please enter valid CarInfo ");
				return Response.status(400).entity(responseInfo).build();
			}
		}
		catch(Exception e) {
			 responseInfo.setCode(500);
			 responseInfo.setMessage("Internal Server Exception . ");
			return Response.status(500).entity(responseInfo).build();
		}
		
			

	}

}
