package com.gcs.bc.loyaltychain.loyaltychainsingle.bigchaindb.proxy;

import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.json.strategy.MetaDataDeserializer;
import com.bigchaindb.json.strategy.MetaDataSerializer;
import com.bigchaindb.model.Account;
import com.bigchaindb.model.FulFill;
import com.bigchaindb.model.MetaData;
import com.bigchaindb.model.MetaDatas;
import com.bigchaindb.model.Transaction;

import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;

@Component
public class BigchainDBProxy {
	
	public BigchainDBProxy(BigchainDBConfig config) {
		this.config = config;
		// configure local connection to bigchaindb network
		BigchainDbConfigBuilder.baseUrl(this.config.getAPIURL()).addToken("app_id", this.config.getAppId())
				.addToken("app_key", this.config.getAppKey()).setup();
	}

	@Autowired
	private BigchainDBConfig config;

	public BigchainDBResponse create(BigchainDBAccount account, BigchainDBTransaction transaction) {

		final String publicKey = account.getPublicKey(); // "302a300506032b657003210033c43dc2180936a2a9138a05f06c892d2fb1cfda4562cbc35373bf13cd8ed373";
		final String privateKey = account.getPrivateKey(); // "302e020100300506032b6570042204206f6b0cd095f1e83fc5f08bffb79c7c8a30e77a3ab65f4bc659026b76394fcea8";

		// set asset data
		// Map<String, String> assetData = new TreeMap<>();
		// assetData.put("msg", "Test");

		Map<String, String> assetData = transaction.getAssetData();

		// set metaData
		// MetaData metaData = new MetaData();
		// metaData.setId("51ce82a14ca274d43e4992bbce41f6fdeb755f846e48e710a3bbb3b0cf8e4204");
		// metaData.setMetaData("msg", "Testing Android");

		MetaData metaData = transaction.getMetaData();

		try {
			// create and send transaction to bigchaindb network
			Transaction retTransaction = BigchainDbTransactionBuilder
					.init()
					.addAssets(assetData, TreeMap.class)
					.addAssetDataClass(TreeMap.class, null).addMetaData(metaData)
					.addMetaDataClassSerializer(MetaData.class, new MetaDataSerializer())
					.addMetaDataClassDeserializer(MetaDatas.class, new MetaDataDeserializer())
					.operation(Operations.CREATE)
					.buildAndSign(
							(EdDSAPublicKey) Account.publicKeyFromHex(publicKey),
							(EdDSAPrivateKey) Account.privateKeyFromHex(privateKey))
					.sendTransaction();
			return BigchainDBResponse.succeed(retTransaction.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return BigchainDBResponse.failed();
	}

	public BigchainDBResponse transfer(BigchainDBAccount accountFrom, BigchainDBAccount accountTo, 
			String transactionId, MetaData metaData) {

		// Describe the output you are fulfilling on the previous transaction
		final FulFill spendFrom = new FulFill();
		spendFrom.setTransactionId(transactionId);
		spendFrom.setOutputIndex("0");

		// Change the metadata if you want
		// MetaData metaData = new MetaData();
		// metaData.setMetaData("what2", "My first BigchainDB transaction");

		// the asset's ID is equal to the ID of the transaction that created it
		String assetId = transactionId;

		// By default, the 'amount' of a created digital asset == "1". So we spend "1"
		// in our TRANSFER.
		String amount = "1";

		// Use the previous transaction's asset and TRANSFER it
		try {
			Transaction transferTransaction = BigchainDbTransactionBuilder
					.init()
					.addMetaData(metaData)
					// source keypair is used in the input, because the current owner is "spending"
					// the output to transfer it
					.addInput(null, spendFrom, (EdDSAPublicKey) Account.publicKeyFromHex(accountFrom.getPublicKey()))
					// after this transaction, the target 'owns' the asset, so, the new output
					// includes the target's public key
					.addOutput(amount, (EdDSAPublicKey) Account.publicKeyFromHex(accountTo.getPublicKey()))
					// reference the asset by ID when doing a transfer
					.addAssets(assetId, String.class).operation(Operations.TRANSFER)
					// the source key signs the transaction to authorize the transfer
					.buildAndSign(
							(EdDSAPublicKey) Account.publicKeyFromHex(accountFrom.getPublicKey()), 
							(EdDSAPrivateKey) Account.privateKeyFromHex(accountFrom.getPrivateKey()))
					.sendTransaction();
			
			return BigchainDBResponse.succeed(transferTransaction.getId());
			
		} catch (IOException | InvalidKeySpecException e) {
			e.printStackTrace();
		}

		return BigchainDBResponse.failed();
	}

	public void setConfig(BigchainDBConfig config) {
		this.config = config;
	}

}
