package com.cmd.carinfo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;

import com.cmd.carinfo.service.ManufactureService;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(ManufactureService.class);
	}
}
