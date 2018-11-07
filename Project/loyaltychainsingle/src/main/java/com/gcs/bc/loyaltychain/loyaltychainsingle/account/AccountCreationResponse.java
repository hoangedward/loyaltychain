package com.gcs.bc.loyaltychain.loyaltychainsingle.account;

import com.gcs.bc.loyaltychain.loyaltychainsingle.core.Response;

public class AccountCreationResponse extends Response {
	
	private String publicKey;
	private String privateKey;
	
	private AccountCreationResponse(int retCode) {
		super(retCode);
	}
	
	private AccountCreationResponse(int retCode, String publicKey, String privateKey) {
		super(retCode);
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	
	public static AccountCreationResponse failed() {
		return new AccountCreationResponse(RET_FAILED);
	}

	public static AccountCreationResponse succeed(String publicKey, String privateKey) {
		return new AccountCreationResponse(RET_SUCCEED, publicKey, privateKey);
	}

	public String getPublicKey() {
		return publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	@Override
	public String toString() {
		return "AccountCreationResponse [publicKey=" + publicKey + ", privateKey=" + privateKey + ", retCode=" + retCode
				+ ", detailMessage=" + detailMessage + "]";
	}

}
