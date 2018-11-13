package com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.db.model.Asset;

@RestController
public class EmbeddedDBProxyTest {
	
	@Autowired
	private EmbeddedDBProxy proxy;
	
	@GetMapping("/id/{id}")
	public String getById(String id) {
		
		Asset asset = proxy.findAssetById(id);
		if(asset == null) {
			return "404";
		}
		return asset.getId();
		
	}
	
	@GetMapping("/name/{name}")
	public String getByName(String name) {
		
		Asset asset = proxy.findAssetByName(name);
		if(asset == null) {
			return "404";
		}
		return asset.getId();
		
	}

}
