package com.gcs.bc.loyaltychain.loyaltychainsingle.account;

import java.security.KeyPair;

import net.i2p.crypto.eddsa.KeyPairGenerator;
import net.i2p.crypto.eddsa.Utils;

public class AccountService {
	
	public enum AccountType {
		BUYER,
		SELLER
	}
	
	public AccountCreationResponse createAccount(String mobileNumber, String passCode, String name, AccountType accountType) {
		
		KeyPairGenerator edDsaKpg = new KeyPairGenerator();
		KeyPair generateKeyPair = edDsaKpg.generateKeyPair();
		
		String publicKey = Utils.bytesToHex(generateKeyPair.getPublic().getEncoded());
		String privateKey = Utils.bytesToHex(generateKeyPair.getPrivate().getEncoded());
		
		return AccountCreationResponse.succeed(publicKey, privateKey);
	}
	
	public static void main(String[] args) {
		AccountService accountService = new AccountService();
		accountService.createAccount("0123456789", "123456", "Alice", AccountType.BUYER);
	}

}
