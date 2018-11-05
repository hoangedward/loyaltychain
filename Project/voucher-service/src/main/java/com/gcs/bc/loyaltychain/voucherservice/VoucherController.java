package com.gcs.bc.loyaltychain.voucherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoucherController {
	
	@Autowired
	private Environment environment;

	@GetMapping("/hello-voucher/{name}")
	public String helloworld(@PathVariable String name) {
		return "Voucher " + name + ". From port: " + environment.getProperty("local.server.port");
	}

}
