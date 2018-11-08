package com.gcs.bc.loyaltychain.loyaltychainsingle.reward;

import java.util.HashMap;
import java.util.Map;

public abstract class Reward {
	
	public enum RewardType {
		Voucher
	}
	
	private String id;
	
	private Map<String, String> info = new HashMap<>();
	
	private MetaData metaData;
	
	public Reward() {
	}
	
	public Reward(String id) {
		this.id = id;
	}
	
	public Reward(Map<String, String> info) {
		this.info = info;
	}
	
	public String getId() {
		return id;
	}

	public Map<String, String> getInfo() {
		return info;
	}
	
	public void setInfo(Map<String, String> info) {
		this.info = info;
	}

	public void addInfo(String key, String value) {
		if(info == null) {
			info = new HashMap<>();
		}
		info.put(key, value);
	}
	
	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}
	
	public MetaData getMetaData() {
		return metaData;
	}
	
	public static class MetaData {
		private String id;
		private Map<String, String> metaData = new HashMap<>();
		public MetaData(String metaDataId, Map<String, String> metaData) {
			// TODO Auto-generated constructor stub
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public Map<String, String> getMetaData() {
			return metaData;
		}
		public void setMetaData(Map<String, String> metaData) {
			this.metaData = metaData;
		}
	}

}
