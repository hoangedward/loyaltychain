package com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.db.model.Asset;
import com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.db.repository.AssetRepository;

@Component
public class EmbeddedDBProxy {

	@Autowired
	private AssetRepository assetRepository;
	
	public Asset findAssetById(String id) {
		Optional<Asset> findById = assetRepository.findById("5beaed6381718705b0132679");
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	
	public Asset findAssetByName(String name) {
		return assetRepository.findByName(name);
	}

}
