package com.gcs.bc.loyaltychain.rewardservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private VoucherServiceProxy proxy;

	@Autowired
	private Environment environment;

	@GetMapping("/hello-reward/{name}")
	public String helloworld(@PathVariable String name) {
		
		String response = proxy.retrieveExchangeValue(name);

		logger.info("{}", response);
		
		return "Reward " + name + ". From port: " + environment.getProperty("local.server.port") + ". ==> Voucher: " + response;
	}
	
}
