package com.gcs.bc.loyaltychain.loyaltychainsingle.core;

public class Response {
	
	public static final int RET_FAILED = -1;
	public static final int RET_SUCCEED = 0;
	
	protected int retCode;
	protected String detailMessage;
	
	public Response(int retCode) {
		this.retCode = retCode;
	}
	
	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	public int getRetCode() {
		return retCode;
	}

	public String getDetailMessage() {
		return detailMessage;
	}
}
