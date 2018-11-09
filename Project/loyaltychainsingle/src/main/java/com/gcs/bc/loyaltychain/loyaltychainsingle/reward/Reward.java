package com.gcs.bc.loyaltychain.loyaltychainsingle.reward;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;

import com.gcs.bc.loyaltychain.loyaltychainsingle.core.Asset;

public abstract class Reward extends Asset implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum RewardType {
		Voucher
	}
	
	public Reward() {
	}
	
	public Reward(String id) {
		this.id = id;
	}
	
	public Reward(Map<String, String> info) {
		this.info = info;
	}
	
	@Override
	public String toString() {
		return "Reward [id=" + id + ", info=" + info + ", metaData=" + metaData + "]";
	}
	
	@Override
	public Object clone() {
		Reward reward = SerializationUtils.clone(this);
		reward.generateId();
		return reward;
	}

}
