package com.gcs.bc.loyaltychain.loyaltychainsingle.reward;

import java.util.Map;

import com.gcs.bc.loyaltychain.loyaltychainsingle.core.Asset.MetaData;
import com.gcs.bc.loyaltychain.loyaltychainsingle.reward.Reward.RewardType;
import com.gcs.bc.loyaltychain.loyaltychainsingle.reward.voucher.Voucher;

public class RewardFactory {

	public static Reward initialize(Map<String, String> info, String metaDataId, Map<String, String> metaData, RewardType rewardType) {
		if(rewardType == null) {
			throw new IllegalArgumentException("rewardType is null");
		}
		switch (rewardType) {
		case Voucher:
			Voucher voucher = new Voucher(info);
			voucher.setMetaData(new MetaData(metaDataId, metaData));
			return voucher;

		default:
			break;
		}
		throw new IllegalArgumentException("Not support reward type: " + rewardType);
	}
	
}
