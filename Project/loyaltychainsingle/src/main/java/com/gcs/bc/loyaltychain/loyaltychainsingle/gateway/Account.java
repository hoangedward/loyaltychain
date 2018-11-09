package com.gcs.bc.loyaltychain.loyaltychainsingle.gateway;

public class Account {
	
	private String publicKey;
	private String privateKey;

	public Account(String publicKey, String privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	@Override
	public String toString() {
		return "Account [publicKey=" + publicKey + ", privateKey=" + privateKey + "]";
	}

}
