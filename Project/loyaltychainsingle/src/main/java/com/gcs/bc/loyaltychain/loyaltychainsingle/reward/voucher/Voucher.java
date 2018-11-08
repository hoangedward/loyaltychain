package com.gcs.bc.loyaltychain.loyaltychainsingle.reward.voucher;

import java.util.Map;

import com.gcs.bc.loyaltychain.loyaltychainsingle.reward.Reward;

public class Voucher extends Reward {
	
	public Voucher() {
		
	}
	
	public Voucher(String id) {
		super(id);
	}

	public Voucher(Map<String, String> info) {
		super(info);
	}

}
