package com.healthdom.rules.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.security.GeneralSecurityException;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiService {
	public static void POST() throws JSONException, IOException, GeneralSecurityException
	{
		 String query_url = "https://debug-service.test.healthdom.com/test/recommendations?access_token=99a5df95-d7fb-4d0b-9d25-20d97ebcb3b6";
         String json = JsonController.buildJson();
         try {
         URL url = new URL(query_url);
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
         conn.setDoOutput(true);
         conn.setDoInput(true);
         conn.setRequestMethod("POST");
         OutputStream os = conn.getOutputStream();
         os.write(json.getBytes("UTF-8"));
         os.close(); 
         // read the response
         InputStream in = new BufferedInputStream(conn.getInputStream());
         String result = IOUtils.toString(in, "UTF-8");
         System.out.println(result);
         in.close();
         conn.disconnect();
         } catch (Exception e) {
 			System.out.println(e);
 		}
	}

}
