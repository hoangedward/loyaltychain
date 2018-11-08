package com.gcs.bc.loyaltychain.loyaltychainsingle.gateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationGatewayTest {

	public static void main(String[] args) {

		ApplicationGateway applicationGateway = new ApplicationGateway();
		
		Account sellerAccount = applicationGateway.createSellerAccount();
		
		Map<String, String> info =  new HashMap<>();
		info.put("Name", "Alice");
		info.put("Age", "28");
		info.put("Company", "GCS");
		
		String metaDataId = "Meta1111111111111";
		Map<String, String> metaData = new HashMap<>();
		metaData.put("Key1", "Value1");
		metaData.put("Key2", "Value2");
		List<String> issuedRewardId = applicationGateway.issueReward(sellerAccount, info, metaDataId, metaData, 3);
		
		System.out.println("Issued reward id: " + issuedRewardId);
		
		if(issuedRewardId.size() > 0 ) {
			Account buyerAccount = applicationGateway.createBuyerAccount();
			
			String newMetaDataId = "Meta222222222222222";
			Map<String, String> newMetaData = new HashMap<>();
			metaData.put("Key3", "Value3");
			metaData.put("Key4", "Value4");
			
			List<String> transferRewardId = applicationGateway.transferReward(
					sellerAccount, buyerAccount, issuedRewardId.get(0), newMetaDataId, newMetaData);
			System.out.println("Transfer id: " + transferRewardId);
			
		}
		
		
		
	}

}
