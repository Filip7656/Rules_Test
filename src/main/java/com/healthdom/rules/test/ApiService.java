package com.healthdom.rules.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;

import com.healthdom.commons.helper.JsonHelper;
import com.healthdom.commons.model.user.User;

public class ApiService {
	public static String POST() throws JSONException, IOException, GeneralSecurityException {
		String query_url = "https://debug-service.test.healthdom.com/test/recommendations?access_token=99a5df95-d7fb-4d0b-9d25-20d97ebcb3b6";
		String json = JsonController.buildJson();
		String result = "";
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
			result = IOUtils.toString(in, "UTF-8");
			in.close();
			conn.disconnect();
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public static List<Object> createAttributesListToSave()
			throws JSONException, IOException, GeneralSecurityException {
		Map<String, Integer> sheetAttributesIndex = JsonController.mapJson();
		User user = JsonHelper.deserialize(POST(), User.class);
		Map<String, String> jsonAttributes = user.getAttributes();
		TreeMap<Integer, String> attributesToSave = new TreeMap<>();

		for (String key : jsonAttributes.keySet()) {
			attributesToSave.put(sheetAttributesIndex.get(key), jsonAttributes.get(key));
		}
		List<Object> list = new ArrayList(attributesToSave.values());
		System.out.println(list);
		return list;

	}
}
