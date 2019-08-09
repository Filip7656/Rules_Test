package roles.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class ApiService {
	public static void POSTRequest() throws IOException {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		Response result = new Response();

		try {
			HttpPost request = new HttpPost("http://debug-service.test.healthdom.com/test/recommendations");
			StringEntity params = new StringEntity(JsonController.buildJson().toString());
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
			httpClient.execute(request);
		} catch (Exception ex) {
			result.setLogError(ex.toString());
		} finally {
			httpClient.close();
		}
	}

	public static void GETRequest() throws IOException {

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://debug-service.test.healthdom.com/test/recommendations");

		HttpResponse response = client.execute(request);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());

	}
}
