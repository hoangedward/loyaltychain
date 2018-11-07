package com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.proxy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class BigchainDBConfig {
	
	public String getAPIURL() {
		return "http://127.0.0.1:9984";
	}
	
	public String getAppId() {
		return StringUtils.EMPTY;
	}
	
	public String getAppKey() {
		return StringUtils.EMPTY;
	}
	
}
