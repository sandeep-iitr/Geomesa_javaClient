import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.simple.JSONObject;

public class SendData {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
		try {

		    HttpPost request = new HttpPost("http://localhost:8083/WebApp/PutData2");
		    
		    //StringEntity params =new StringEntity("details={\"name\":\"myname\",\"age\":\"20\"} ");
		    
		    Double MIN_X = -79.5;
	        Double MIN_Y =  37.0;
	        DateTime MIN_DATE = new DateTime(2016, 1, 1, 0, 0, 0, DateTimeZone.forID("UTC"));
	        Random random = new Random(5771);
	        Double DX = 2.0;
	        Double DY = 2.0;
	        Long SECONDS_PER_YEAR = 365L * 24L * 60L * 60L;
	        
	       
	        double x = MIN_X + random.nextDouble() * DX;
	        double y = MIN_Y + random.nextDouble() * DY;
	        DateTime dateTime = MIN_DATE.plusSeconds((int) Math.round(random.nextDouble() * SECONDS_PER_YEAR));
	            
		    JSONObject Data = new JSONObject();
		    Data.put("name", "water data");
		    Data.put("data_type", "temperature");
		    Data.put("unit", "degree celcius");
		    Data.put("value", "25");
		    Data.put("timeStamp", dateTime.getMillis());
		    
		    JSONObject Coord = new JSONObject();
		    Coord.put("lat", x);
		    Coord.put("lng", y);
		    
		    Data.put("location", Coord);
		    
		    StringEntity params =new StringEntity(Data.toJSONString());
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    HttpResponse response = httpClient.execute(request);
		    //System.out.println("Sending Data:");
		    
	       
	        
		    //System.out.println("Data is send");
		    //handle response here...

		}catch (Exception ex) {

		    //handle exception here

		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
		
	}

}
