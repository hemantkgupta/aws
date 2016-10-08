import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;


public class ScanTable {

	public static void main(String[] args) {
		AmazonDynamoDBClient client = getClient();
		
		ScanRequest scanRequest = new ScanRequest()
	    						  .withTableName("Reply");

		ScanResult result = client.scan(scanRequest);
		for (Map<String, AttributeValue> item : result.getItems()){
		    //printItem(item);
			System.out.println(item.toString());
		}

	}
	
	private static AmazonDynamoDBClient getClient(){
		/*
         * The ProfileCredentialsProvider will return your [default]
         * credential profile by reading from the credentials file located at
         * (/Users/ghemant/.aws/credentials).
         */
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider("default").getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (/Users/ghemant/.aws/credentials), and is in valid format.",
                    e);
        }
        
        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		
		// This client will default to US West (Oregon)
		AmazonDynamoDBClient client = new AmazonDynamoDBClient(credentials);
		client.setRegion(usWest2);
		
		
		return client;
	}

}