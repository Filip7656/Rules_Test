package com.healthdom.rules.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;

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
