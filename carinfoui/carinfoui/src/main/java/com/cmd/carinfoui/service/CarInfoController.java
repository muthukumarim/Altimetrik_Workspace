package com.cmd.carinfoui.service;

import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.cmd.carinfoui.model.CarDetails;
import com.cmd.carinfoui.model.CarDetailsResponse;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
@Controller
public class CarInfoController {

	
	
	
	
	
	@GetMapping("/welcome")
	public String GetMessage(Model model) {
		
		//model.addAttribute("message","Welcome to portal");
		return "welcome";
	
		
	}
	
	
	@RequestMapping(value="/getcarinfo", method=RequestMethod.GET)
    public String mainWithParam(
            @RequestParam("carname") 
			String carInfo, Model model) {
		String url="http://localhost:8080/service/v1/getInfo?carname=<name>";
				url=url.replaceAll("<name>", carInfo);
		RestTemplate template =new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		 String result = template.getForObject(url, String.class);
		 mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		 mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		 mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		 try {
			CarDetailsResponse carMfInfo = mapper.readValue(result, CarDetailsResponse.class);
			List<CarDetails> carDetails =carMfInfo.getCarDetails();
			model.addAttribute("carDetails", carDetails);
		 }catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        model.addAttribute("result", result);

        return "welcome"; //view
    }
}

