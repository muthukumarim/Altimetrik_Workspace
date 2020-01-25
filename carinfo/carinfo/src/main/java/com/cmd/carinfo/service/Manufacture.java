package com.cmd.carinfo.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.cmd.carinfo.model.CarDetailsResponse;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@Service
@Path("/service/v1")
public interface Manufacture {

	@GET
	@Path("/healthcheck")
	public Response healthCheck(@QueryParam("message") String message);
	
	@GET
	@Path("/getInfo")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( value ="get the Car Manufacture Details info",
	response= CarDetailsResponse.class
			)
	@ApiResponses(value= {
			@ApiResponse(code=404, message="service not availabe"),
			@ApiResponse(code=500,message="unexpected runtime error")
	}
			)
	public Response getManufactureInfo(@QueryParam("carname") String carName);
}
