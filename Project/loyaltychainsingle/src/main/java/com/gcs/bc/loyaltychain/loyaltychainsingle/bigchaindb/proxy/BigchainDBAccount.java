package com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.proxy;

public class BigchainDBAccount {
	
	private String publicKey;
	private String privateKey;

	public BigchainDBAccount(String publicKey, String privateKey) {
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
		return "BigchainDBAccount [publicKey=" + publicKey + ", privateKey=" + privateKey + "]";
	}

}
