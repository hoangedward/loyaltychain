package com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.proxy;

import java.util.Map;

import com.google.gson.internal.LinkedTreeMap;

public class BigchainDBProxyTest {

	public static void main(String[] args) {
		BigchainDBProxy bigchainDBProxy = new BigchainDBProxy(new BigchainDBConfig());
		//bigchainDBProxy.setConfig(new BigchainDBConfig());
		
		final String publicKey = "302a300506032b657003210033c43dc2180936a2a9138a05f06c892d2fb1cfda4562cbc35373bf13cd8ed373";
	    final String privateKey = "302e020100300506032b6570042204206f6b0cd095f1e83fc5f08bffb79c7c8a30e77a3ab65f4bc659026b76394fcea8";
		BigchainDBAccount account = new BigchainDBAccount(publicKey, privateKey);
		
		BigchainDBTransaction transaction = new BigchainDBTransaction();
		Map<String, String> assetData = new LinkedTreeMap<>();
		assetData.put("Name", "Alice");
		assetData.put("Age", "28");
		assetData.put("Company", "GCS");
		transaction.setAssetData(assetData);
		
		String metaDataId = "Meta11111111111111111111111111";
		Map<String, String> medaData = new LinkedTreeMap<>();
		medaData.put("Key1", "Value1");
		medaData.put("Key2", "Value2");
		transaction.setMetaData(metaDataId, medaData);
		
		BigchainDBResponse createResponse = bigchainDBProxy.create(account, transaction);
		System.out.println(createResponse);
	}

}
