package com.gcs.bc.loyaltychain.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
	
	@Autowired
	private Environment environment;

	@GetMapping("/hello-account/{name}")
	public String helloworld(@PathVariable String name) {
		return "Account " + name + ". From port: " + environment.getProperty("local.server.port");
	}
	
}
