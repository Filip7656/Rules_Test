package com.healthdom.rules.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.healthdom.commons.helper.JsonHelper;
import com.healthdom.commons.model.user.User;
import com.healthdom.rules.test.components.AttributesModel;
import com.healthdom.rules.test.components.SheetObjectModel;

class ApiService {
	private static Logger LOG = LoggerFactory.getLogger(ApiService.class);

	static List<SheetObjectModel> addResponseFromApitoTestObjects(List<SheetObjectModel> testObjects,
			AttributesModel attributes) throws JSONException, IOException, GeneralSecurityException {
		List<User> users = new ArrayList<>();
		List<String> apiResponse = postApiCall(testObjects, attributes);
		List<Map<String, String>> jsonAttributes = new ArrayList<>();
		parseResponseToUser(users, apiResponse, jsonAttributes);
		mergeResponseWithTestObjects(attributes, testObjects, jsonAttributes);
		return testObjects;

	}

	private static void mergeResponseWithTestObjects(AttributesModel attributes, List<SheetObjectModel> testObjects,
			List<Map<String, String>> jsonAttributes) {
		Map<Integer, String> finalMap = new HashMap<>();
		TreeMap<Integer, String> attributesMap= new TreeMap<>();
		for (int e = 0; e < attributes.getAttributes().size(); e++) {
			attributesMap.put(e, attributes.getAttributes().get(e).toString());
		}
		List <Object> attributesCollumnNumber = new ArrayList<>(attributesMap.keySet());
		List <Object> attributesNames = new ArrayList<>(attributesMap.values());
		

		
		for (int w = 0; w < jsonAttributes.size(); w++) {
				List <Object> listOfJsonKeys = new ArrayList<>(jsonAttributes.get(w).keySet());
				List <Object> listOfJsonValues = new ArrayList<>(jsonAttributes.get(w).values());
				
				// tutaj dac fora dla attributeNames
				// q
				//   f
				//		porownac
				for (int f = 0; f < listOfJsonKeys.size(); f++) {
					if(listOfJsonKeys.get(f).equals(attributesNames.get(arg0))){
						
					
						
					}else if(true) {
						
					}else if(true) {
						
					}
				}
		
		
		
		
		for (int i = 0; i < jsonAttributes.size(); i++) {
			List<Object> responseFromApiToSave = new ArrayList<>();
		//	responseFromApiToSave.add(arg0, arg1);
			
			testObjects.get(i).setResponseFromApi(responseFromApiToSave);
			// make list match attributes names in worksheet
			for (int y = 0; y < 6; y++) {
				testObjects.get(i).getResponseFromApi().add(y, " ");
			}
		}
	}
	}
	private static void parseResponseToUser(List<User> users, List<String> apiResponse,
			List<Map<String, String>> jsonAttributes) {
		for (int i = 0; i < apiResponse.size(); i++) {
			users.add(JsonHelper.deserialize(apiResponse.get(i), User.class));
			jsonAttributes.add(i, users.get(i).getAttributes());
		}
	}

	private static List<String> postApiCall(List<SheetObjectModel> testObjects, AttributesModel attributes)
			throws JSONException, IOException, GeneralSecurityException {
		List<String> json = JsonController.buildJson(testObjects, attributes);
		List<String> resultsFromApi = new ArrayList<String>();
		requestResponseFromApi(json, resultsFromApi);
		return resultsFromApi;
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
				os.write(json.get(i).getBytes(Consts.CHAR_CODING_TYPE));
				os.close();
				InputStream in = new BufferedInputStream(apiConnection.getInputStream());
				resultsFromApi.add(IOUtils.toString(in, Consts.CHAR_CODING_TYPE));
				in.close();
				apiConnection.disconnect();
				LOG.info(resultsFromApi.get(i));
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}
	}
}
