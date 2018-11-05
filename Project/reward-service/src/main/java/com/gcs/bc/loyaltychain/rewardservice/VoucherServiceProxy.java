package com.gcs.bc.loyaltychain.rewardservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="voucher-service", url="localhost:8200")
//@FeignClient(name="voucher-service")

@FeignClient(name="api-gateway")
@RibbonClient(name="voucher-service")
public interface VoucherServiceProxy {
	
	// http://localhost:8765/voucher-service/hello-voucher/edward
	@GetMapping("/voucher-service/hello-voucher/{name}")
	public String retrieveExchangeValue(@PathVariable("name") String name);

}
