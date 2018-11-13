package com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.db.repository")
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "bigchaindb";
	}

	@Override
	@Bean
	public MongoClient mongoClient() {
		return new MongoClient("127.0.0.1", 27017);
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.db.model";
	}
}
