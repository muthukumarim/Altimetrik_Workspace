package com.cmd.carinfo;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.cmd.carinfo.model.CarDetailsResponse;
import com.cmd.carinfo.model.ResponseInfo;
import com.cmd.carinfo.service.ManufactureService;

@SpringBootTest
class CarinfoApplicationTests {

	@Mock
	private RestTemplate restTemplate;

	@Spy
	private final ManufactureService manufactureService = new ManufactureService();

	/*
	 * @Before public void setUp() { ReflectionTestUtils.setField(t, "value",
	 * "https://geocode.xyz/inputvalue?geoit=json"); }
	 */
	@Test
	public void health() {

		javax.ws.rs.core.Response res = manufactureService.healthCheck("test");
		String result = (String) res.getEntity();
		System.err.println(result);
		Assert.assertEquals("helath check", "Hello test", result);
	}

	@Test
	public void serviceSuccessScenario() {

		Response res = manufactureService.getManufactureInfo("benz");
		CarDetailsResponse carDetailsResponse = (CarDetailsResponse) res.getEntity();
		Assert.assertEquals(carDetailsResponse.getCarDetails().size(), 7);
	}

	@Test
	public void servicefailureScenario1() {

		Response res = manufactureService.getManufactureInfo("12");
	     ResponseInfo info= (ResponseInfo) res.getEntity();
		Assert.assertEquals(info.getCode(), 400);
	}
	
	@Test
	public void servicefailureScenario2() {

		Response res = manufactureService.getManufactureInfo(" ");
		 ResponseInfo info= (ResponseInfo) res.getEntity();
			Assert.assertEquals(info.getCode(), 500);
	}
	@Test
	public void servicefailureScenarionull() {

		Response res = manufactureService.getManufactureInfo(null);
		 ResponseInfo info= (ResponseInfo) res.getEntity();
			Assert.assertEquals(info.getCode(), 400);
	}

}
