package edward.helloworld;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bigchaindb.api.AssetsApi;
import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.json.strategy.MetaDataDeserializer;
import com.bigchaindb.json.strategy.MetaDataSerializer;
import com.bigchaindb.model.Account;
import com.bigchaindb.model.Assets;
import com.bigchaindb.model.MetaData;
import com.bigchaindb.model.MetaDatas;
import com.bigchaindb.model.Transaction;

import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;

@RestController
public class HelloWorldController {

	@RequestMapping(method=RequestMethod.GET, path= "helloworld")
	public String helloWorld() {
		
		//display assets 
        String data = "xxx";
		
		final String publicKey = "302a300506032b657003210033c43dc2180936a2a9138a05f06c892d2fb1cfda4562cbc35373bf13cd8ed373";
	    final String privateKey = "302e020100300506032b6570042204206f6b0cd095f1e83fc5f08bffb79c7c8a30e77a3ab65f4bc659026b76394fcea8";
		
	    //set asset data
        Map<String, String> assetData = new TreeMap<>();
        assetData.put("msg", "Test");

        //configure local connection to bigchaindb network
        BigchainDbConfigBuilder
                .baseUrl("http://127.0.0.1:9984")
                .addToken("app_id", "") 
                .addToken("app_key", "").setup();

        //set metadata
        MetaData metaData = new MetaData();
        metaData.setId("51ce82a14ca274d43e4992bbce41f6fdeb755f846e48e710a3bbb3b0cf8e4204");
        metaData.setMetaData("msg", "Testing Android");
        
        try {
            //create and send transaction to bigchaindb network
            Transaction transaction = BigchainDbTransactionBuilder
                    .init()
                    .addAssets(assetData, TreeMap.class)
                    .addAssetDataClass(TreeMap.class, null)
                    .addMetaData(metaData)
                    .addMetaDataClassSerializer(MetaData.class, new MetaDataSerializer())
                    .addMetaDataClassDeserializer(MetaDatas.class, new MetaDataDeserializer())
                    .operation(Operations.CREATE)
                    .buildAndSign(
                            (EdDSAPublicKey) Account.publicKeyFromHex(publicKey),
                            (EdDSAPrivateKey) Account.privateKeyFromHex(privateKey))
                    .sendTransaction();

            //query assets (Mondo clients can also be used to use mongo queries)
            Assets assets = AssetsApi.getAssets("Test");
            
            if(assets.getAssets().size() > 0){
                data = assets.getAssets().get(0).getData().toString();
            }
            else {
                data = "No Assets found";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return data;
	}
	
}
