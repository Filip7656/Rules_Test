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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.healthdom.commons.helper.JsonHelper;
import com.healthdom.commons.model.user.User;
import com.healthdom.rules.test.components.AttributesObject;
import com.healthdom.rules.test.components.SheetObject;

public class ApiService {
	private static Logger LOG = LoggerFactory.getLogger(ApiService.class);

	public static List<String> postApiCall(List<SheetObject> testObjects, AttributesObject attributes)
			throws JSONException, IOException, GeneralSecurityException {
		List<String> json = JsonController.buildJson(testObjects, attributes);
		List<String> resultsFromApi = new ArrayList<String>();
		requestResponseFromApi(json, resultsFromApi);
		return resultsFromApi;
	}

	public static List<List<Object>> createAttributesListToSave(List<SheetObject> testObjects,
			AttributesObject attributes) throws JSONException, IOException, GeneralSecurityException {

		List<Map<String, Integer>> sheetAttributesIndex = JsonController.mapJson(testObjects, attributes);
		List<User> users = new ArrayList<User>();
		List<String> apiResponse = postApiCall(testObjects, attributes);
		List<Map<String, String>> jsonAttributes = new ArrayList<Map<String, String>>();

		for (int i = 0; i < apiResponse.size(); i++) {
			users.add(JsonHelper.deserialize(apiResponse.get(i), User.class));
			jsonAttributes.add(i, users.get(i).getAttributes());
		}

		List<TreeMap<Integer, String>> attributesToSave = new ArrayList<TreeMap<Integer, String>>();

		for (int i = 0; i < jsonAttributes.size(); i++) {
			for (String key : jsonAttributes.get(i).keySet()) {
				attributesToSave.get(i).put(sheetAttributesIndex.get(i).get(key), jsonAttributes.get(i).get(key));
			}
		}

	//	List<Object> list = new ArrayList<Object>(attributesToSave.values());
		List<List<Object>> listOfResponses = new ArrayList<List<Object>>();

		for (int i = 0; i < attributesToSave.size(); i++) {
			listOfResponses.add((List)attributesToSave.get(i).values());
			
			for (int y = 0; y < 8; y++) {
				listOfResponses.get(i).add(y, " ");
			}
			
		}

	
		LOG.info(listOfResponses.toString());
		return listOfResponses;

	}

	private static void requestResponseFromApi(List<String> json, List<String> resultsFromApi) {
		for (int i = 0; i < json.size(); i++) {
			try {
				URL healthdomApi = new URL(Consts.DEBUG_API_URL);
				HttpURLConnection apiConnection = (HttpURLConnection) healthdomApi.openConnection();
				apiConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				apiConnection.setDoOutput(true);
				apiConnection.setDoInput(true);
				apiConnection.setRequestMethod("POST");
				OutputStream os = apiConnection.getOutputStream();
				os.write(json.get(i).getBytes("UTF-8"));
				os.close();
				// read the response
				InputStream in = new BufferedInputStream(apiConnection.getInputStream());
				resultsFromApi.add(IOUtils.toString(in, "UTF-8"));
				in.close();
				apiConnection.disconnect();
				LOG.info(resultsFromApi.get(i));
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}
	}
}
