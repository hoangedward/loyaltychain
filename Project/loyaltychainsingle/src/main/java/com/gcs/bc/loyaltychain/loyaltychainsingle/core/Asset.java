package com.gcs.bc.loyaltychain.loyaltychainsingle.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Asset implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 1L;

	protected String id;
	
	protected Map<String, String> info = new HashMap<>();
	
	protected MetaData metaData;
	
	public Asset() {
		generateId();
	}
	
	protected void generateId() {
		info.put("ID", Long.toString(System.nanoTime()));
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
	
	public static class MetaData implements Cloneable, Serializable {
		private static final long serialVersionUID = 1L;
		
		private String id;
		private Map<String, String> metaData = new HashMap<>();
		public MetaData(String metaDataId, Map<String, String> metaData) {
			this.id = metaDataId;
			this.metaData = metaData;
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
		
		@Override
		public String toString() {
			return "MetaData [id=" + id + ", metaData=" + metaData + "]";
		}
	}
	
}
