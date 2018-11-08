package com.gcs.bc.loyaltychain.loyaltychainsingle.core;

public abstract class Response {
	
	public static final int RET_FAILED = -1;
	public static final int RET_SUCCEED = 0;
	
	protected int retCode = RET_FAILED;
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
	
	public boolean isSucceed() {
		return retCode == RET_SUCCEED;
	}
	
	public boolean isFailed() {
		return retCode != RET_SUCCEED;
	}
}
