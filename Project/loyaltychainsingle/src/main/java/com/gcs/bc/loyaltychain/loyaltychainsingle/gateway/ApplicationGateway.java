package com.gcs.bc.loyaltychain.loyaltychainsingle.gateway;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.gcs.bc.loyaltychain.loyaltychainsingle.account.AccountCreationResponse;
import com.gcs.bc.loyaltychain.loyaltychainsingle.account.AccountService;
import com.gcs.bc.loyaltychain.loyaltychainsingle.account.AccountService.AccountType;
import com.gcs.bc.loyaltychain.loyaltychainsingle.reward.Reward;
import com.gcs.bc.loyaltychain.loyaltychainsingle.reward.Reward.RewardType;
import com.gcs.bc.loyaltychain.loyaltychainsingle.reward.RewardFactory;
import com.gcs.bc.loyaltychain.loyaltychainsingle.reward.RewardService;

@RestController
public class ApplicationGateway {
	
	

	public Account createSellerAccount() {
		
		AccountCreationResponse createRet = new AccountService().createAccount("0123456789", "123456", "Alice", AccountType.SELLER);
		
		if(createRet.isFailed()) {
			return null;
		}
		
		return new Account(createRet.getPublicKey(), createRet.getPrivateKey());
	}
	
	public Account createBuyerAccount() {
		AccountCreationResponse createRet = new AccountService().createAccount("0123456789", "123456", "Alice", AccountType.BUYER);
		
		if(createRet.isFailed()) {
			return null;
		}
		
		return new Account(createRet.getPublicKey(), createRet.getPrivateKey());
	}
	
	public void login() {
		
	}
	
	public void getRewardList() {
		
	}
	
	public List<String> issueReward(Account account, Map<String, String> info, String metaDataId, Map<String, String> metaData, int quantity) {
		
		Reward reward = RewardFactory.initialize(info, metaDataId, metaData, RewardType.Voucher);
		
		List<String> retIdList = new RewardService().issue(account, reward, quantity);
		
		return retIdList;
		
	}
	
	public void transferReward() {
		
	}

	public List<String> transferReward(Account fromAccount, Account toAccount, String transactionId, String metaDataId,
			Map<String, String> metaData) {
		List<String> retIdList = new RewardService().transfer(fromAccount, toAccount, transactionId, metaDataId, metaData);
		
		return retIdList;
	}
	
}
