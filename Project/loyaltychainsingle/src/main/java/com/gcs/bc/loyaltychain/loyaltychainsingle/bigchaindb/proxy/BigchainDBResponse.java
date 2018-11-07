package com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.proxy;

import org.apache.commons.lang3.StringUtils;

import com.gcs.bc.loyaltychain.loyaltychainsingle.core.Response;

public class BigchainDBResponse extends Response {
	
	private String transactionId;
	
	private BigchainDBResponse(int retCode, String transactionId) {
		super(retCode);
		this.transactionId = transactionId;
	}
	
	public static BigchainDBResponse failed() {
		return new BigchainDBResponse(RET_FAILED, StringUtils.EMPTY);
	}

	public static BigchainDBResponse succeed(String transactionId) {
		return new BigchainDBResponse(RET_SUCCEED, transactionId);
	}

	public int getRetCode() {
		return retCode;
	}

	public String getTransactionId() {
		return transactionId;
	}

	@Override
	public String toString() {
		return "BigchainDBResponse [transactionId=" + transactionId + ", retCode=" + retCode + ", detailMessage="
				+ detailMessage + "]";
	}

}
