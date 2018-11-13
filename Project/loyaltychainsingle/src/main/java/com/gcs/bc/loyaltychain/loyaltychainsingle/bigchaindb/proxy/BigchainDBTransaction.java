package com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.proxy;

import java.util.Map;

import com.bigchaindb.model.MetaData;
import com.google.gson.internal.LinkedTreeMap;

public class BigchainDBTransaction {

	private MetaData metaData;
	private Map<String, String> assetData;

	public Map<String, String> getAssetData() {
		return assetData;
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public void setAssetData(Map<String, String> data) {
		assetData = new LinkedTreeMap<>();
		data.forEach((k, v) -> assetData.put(k, v));
	}

	public void setMetaData(String id, Map<String, String> data) {
		metaData = new MetaData();
		metaData.setId(id);
		data.forEach((k, v) -> metaData.setMetaData(k, v));
	}

	@Override
	public String toString() {
		return "BigchainDBTransaction [metaData=" + metaData + ", assetData=" + assetData + "]";
	}
	
}
