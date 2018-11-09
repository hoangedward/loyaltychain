package com.gcs.bc.loyaltychain.loyaltychainsingle.reward;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.proxy.BigchainDBAccount;
import com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.proxy.BigchainDBConfig;
import com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.proxy.BigchainDBProxy;
import com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.proxy.BigchainDBResponse;
import com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.proxy.BigchainDBTransaction;
import com.gcs.bc.loyaltychain.loyaltychainsingle.core.Asset.MetaData;
import com.gcs.bc.loyaltychain.loyaltychainsingle.gateway.Account;

public class RewardService {

	public void issue() {

	}

	public void transfer() {

	}

	public List<String> issue(Account account, Reward reward, int quantity) {

		List<String> retIdList = new ArrayList<>();

		if (quantity < 1) {
			return retIdList;
		}

		BigchainDBProxy bigchainDBProxy = new BigchainDBProxy(new BigchainDBConfig());
		//bigchainDBProxy.setConfig(new BigchainDBConfig());

		BigchainDBAccount bigchainDBAccount = new BigchainDBAccount(account.getPublicKey(), account.getPrivateKey());

		for (int i = 0; i < quantity; i++) {
			
			Reward newReward = (Reward)reward.clone();
			
			BigchainDBTransaction transaction = new BigchainDBTransaction();
			transaction.setAssetData(newReward.getInfo());

			MetaData metaData = newReward.getMetaData();
			if (metaData != null) {
				transaction.setMetaData(metaData.getId(), metaData.getMetaData());
			}
			BigchainDBResponse createRet = bigchainDBProxy.create(bigchainDBAccount, transaction);
			if (createRet.isSucceed()) {
				retIdList.add(createRet.getTransactionId());
			}
		}

		return retIdList;
	}

	public List<String> transfer(Account fromAccount, Account toAccount, String transactionId, String metaDataId,
			Map<String, String> metaData) {
		
		List<String> retIdList = new ArrayList<>();
		
		BigchainDBProxy bigchainDBProxy = new BigchainDBProxy(new BigchainDBConfig());
		//bigchainDBProxy.setConfig(new BigchainDBConfig());

		BigchainDBAccount fromBigchainDBAccount = new BigchainDBAccount(fromAccount.getPublicKey(), fromAccount.getPrivateKey());
		BigchainDBAccount toBigchainDBAccount = new BigchainDBAccount(toAccount.getPublicKey(), toAccount.getPrivateKey());
		
		com.bigchaindb.model.MetaData bigchainMetaData = new com.bigchaindb.model.MetaData();
		if(metaDataId != null) {
			bigchainMetaData.setId(metaDataId);
		}
		if(metaData != null) {
			metaData.forEach((k, v) -> bigchainMetaData.setMetaData(k, v));
		}
		
		BigchainDBResponse transferRet = bigchainDBProxy.transfer(fromBigchainDBAccount, toBigchainDBAccount, transactionId, bigchainMetaData);
		if(transferRet.isSucceed()) {
			retIdList.add(transferRet.getTransactionId());
		}
		
		return retIdList;
	}

}
