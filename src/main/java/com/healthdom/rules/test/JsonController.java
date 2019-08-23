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
import com.healthdom.rules.test.components.AttributesModel;
import com.healthdom.rules.test.components.JsonModel;
import com.healthdom.rules.test.components.SheetObjectModel;

class JsonController {
	private static Logger LOG = LoggerFactory.getLogger(JsonController.class);

	static List<String> buildJson(List<SheetObjectModel> testObjects, AttributesModel attributes)
			throws JSONException, IOException, GeneralSecurityException {
		List<String> responseFromApi = new ArrayList<String>();
		for (SheetObjectModel sheetObject : testObjects) {
			JsonModel json = buildJsonModel(attributes, sheetObject);
			String jsonHandler = jsonObjectHandler(json);
			responseFromApi.add(jsonHandler);
		}
		return responseFromApi;
	}

	private static JsonModel buildJsonModel(AttributesModel attributes, SheetObjectModel sheetObject) {
		JsonModel json = new JsonModel(sheetObject.getRulesResult().get(Consts.USER_TIMESTAMP_ROW).toString(),
				sheetObject.getRulesResult().get(Consts.USER_PAYMENTPLAN_ROW).toString(), attributes.getAttributes(),
				sheetObject.getRulesResult());
		return json;
	}
	
	private static String jsonObjectHandler(JsonModel json) {
		Map<Object, Object> attributesObject = new HashMap<>();
		Map<Object, Object> medicalTestsObject = new HashMap<>();
		Map<Object, Object> medicalTestsDueInObject = new HashMap<>();
	
		jsonDividerAndParser(json, attributesObject, medicalTestsObject, medicalTestsDueInObject);

		HashMap<String, Object> jsonObject = new HashMap<String, Object>();
		jsonAddData(json, attributesObject, medicalTestsObject, medicalTestsDueInObject, jsonObject);
		Gson gson = new Gson();
		String j = gson.toJson(jsonObject);
		return j;
	}

	private static void jsonAddData(JsonModel json, Map<Object, Object> attributesObject,
			Map<Object, Object> medicalTestsObject, Map<Object, Object> medicalTestsDueInObject,
			HashMap<String, Object> jsonObject) {
		jsonObject.put("timestamp", json.getTimestamp());
		jsonObject.put("paymentPlanVariant", json.getPaymentPlanVariant());
		jsonObject.put("attributes", attributesObject);
		jsonObject.put("medicalTests", medicalTestsObject);
		jsonObject.put("medicalTestsDueIn", medicalTestsDueInObject);
	}

	private static void jsonDividerAndParser(JsonModel json, Map<Object, Object> attributesObject,
			Map<Object, Object> medicalTestsObject, Map<Object, Object> medicalTestsDueInObject) {
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
	}

}
