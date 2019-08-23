package com.healthdom.rules.test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.healthdom.rules.test.components.AttributesObject;
import com.healthdom.rules.test.components.JsonModel;
import com.healthdom.rules.test.components.SheetObject;
import com.sun.tools.sjavac.Log;

public class JsonController {
	private static Logger LOG = LoggerFactory.getLogger(JsonController.class);

	public static List<String> buildJson(List<SheetObject> testObjects, AttributesObject attributes)
			throws JSONException, IOException, GeneralSecurityException {
		List<String> responseFromApi = new ArrayList<String>();
		for (SheetObject sheetObject : testObjects) {
			JsonModel json = buildJsonModel(attributes, sheetObject);
			String j = jsonObjectHandler(json);
			responseFromApi.add(j);
		}
		return responseFromApi;
	}

	private static JsonModel buildJsonModel(AttributesObject attributes, SheetObject sheetObject) {
		JsonModel json = new JsonModel(sheetObject.getRulesResult().get(Consts.USER_TIMESTAMP_ROW).toString(),
				sheetObject.getRulesResult().get(Consts.USER_PAYMENTPLAN_ROW).toString(), attributes.getAttributes(),
				sheetObject.getRulesResult());
		return json;
	}

	public static List<Map<String, Integer>> mapJson(List<SheetObject> testObjects, AttributesObject attributes)
			throws IOException, GeneralSecurityException {
		List<Map<String, Integer>> jsonMap = new ArrayList<Map<String, Integer>>();
		for (SheetObject sheetObject : testObjects) {
			JsonModel json = buildJsonModel(attributes, sheetObject);
			mapJsonHandler(jsonMap, json);
		}
		return jsonMap;
	}

	private static String jsonObjectHandler(JsonModel json) {
		Map<Object, Object> attributesObject = new HashMap<>();
		Map<Object, Object> medicalTestsObject = new HashMap<>();
		Map<Object, Object> medicalTestsDueInObject = new HashMap<>();
		for (int i = 0; i < json.getListSize(); i++) {
			String data = (String) json.getAttributeId(i);
			if (data.isEmpty()) {
				continue;
			}
			String delims = "[.]";
			String[] tokens = data.split(delims);
			if (tokens[0].equals("attributes")) {
				attributesObject.put((String) tokens[1], json.getAttributeValue(i));
			} else if (tokens[0].equals("medicalTests")) {
				medicalTestsObject.put((String) tokens[1], json.getAttributeValue(i));
			} else if (tokens[0].equals("medicalTestsDueIn")) {
				medicalTestsDueInObject.put((String) tokens[1], json.getAttributeValue(i));

			}
		}

		HashMap<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("timestamp", json.getTimestamp());
		jsonObject.put("paymentPlanVariant", json.getPaymentPlanVariant());
		jsonObject.put("attributes", attributesObject);
		jsonObject.put("medicalTests", medicalTestsObject);
		jsonObject.put("medicalTestsDueIn", medicalTestsDueInObject);
		Gson gson = new Gson();
		String j = gson.toJson(jsonObject);
		System.out.println(j);
		return j;
	}

	private static 	List<Map<String, Integer>>  mapJsonHandler(List<Map<String, Integer>> jsonMap, JsonModel json) {
		Map<String, Integer> temporaryHandler = new HashMap<String, Integer>();	
		for (int y = 0; y < json.getListSize(); y++) {
				String data = (String) json.getAttributeId(y);
				if (data.isEmpty()) {
					continue;
				}
				String delims = "[.]";
				String[] tokens = data.split(delims);
				int key =json.getAttributeIds().indexOf(data) + 8;
				temporaryHandler.put(tokens[0], key);
				jsonMap.add(temporaryHandler);
				LOG.info(temporaryHandler.toString());

			}
		
		return jsonMap;
	}

}
