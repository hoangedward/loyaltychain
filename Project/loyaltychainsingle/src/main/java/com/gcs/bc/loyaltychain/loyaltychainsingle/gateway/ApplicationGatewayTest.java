package com.gcs.bc.loyaltychain.loyaltychainsingle.gateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bigchaindb.api.AssetsApi;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Assets;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.model.Transactions;

public class ApplicationGatewayTest {

	public static void main(String[] args) throws Exception {
		
		// https://github.com/bigchaindb/java-bigchaindb-driver

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
			newMetaData.put("Key3", "Value3");
			newMetaData.put("Key4", "Value4");
			
			List<String> transferRewardId = applicationGateway.transferReward(
					sellerAccount, buyerAccount, issuedRewardId.get(0), newMetaDataId, newMetaData);
			System.out.println("Transfer id: " + transferRewardId);
			
			
			try {
				Transaction transferedTransaction = TransactionsApi.getTransactionById(transferRewardId.get(0));
				System.out.println(transferedTransaction);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			Transaction issuedTransaction = TransactionsApi.getTransactionById(issuedRewardId.get(0));
			System.out.println(issuedTransaction);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Transactions transactionsByAssetId = TransactionsApi.getTransactionsByAssetId(issuedRewardId.get(0), Operations.CREATE);
			System.out.println(transactionsByAssetId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Assets assets = AssetsApi.getAssets("Alice");
			System.out.println(assets);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
