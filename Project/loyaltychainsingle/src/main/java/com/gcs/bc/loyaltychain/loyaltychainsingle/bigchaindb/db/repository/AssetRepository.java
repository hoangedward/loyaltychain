package com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.db.model.Asset;

public interface AssetRepository extends MongoRepository<Asset, String> /*, QuerydslPredicateExecutor<Asset>*/ {
	
	public Asset findByName(String name);

}
