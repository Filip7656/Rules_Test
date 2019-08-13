package com.healthdom.rules.test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class JsonController {
	private static String getTimestamp() throws IOException, GeneralSecurityException {
		return (String) SheetUtilities.getColumn(3, SheetUtilities.openSheet()).get(4);
	}

	private static String getPaymentPlanVariant() throws IOException, GeneralSecurityException {
		return (String) SheetUtilities.getColumn(3, SheetUtilities.openSheet()).get(5);
	}

	private static List<Object> getattributeId() throws IOException, GeneralSecurityException {
		List<Object> attributeId = SheetUtilities.getColumn(0, SheetUtilities.openSheet()).subList(7,
				SheetUtilities.getColumn(0, SheetUtilities.openSheet()).size());
		return attributeId;

	}

	private static List<Object> getattributeValue() throws IOException, GeneralSecurityException {
		List<Object> attributeValue = SheetUtilities.getColumn(3, SheetUtilities.openSheet()).subList(7,
				SheetUtilities.getColumn(3, SheetUtilities.openSheet()).size());
		return attributeValue;

	}

	public static String buildJson() throws JSONException, IOException, GeneralSecurityException {
		JsonComponent json = new JsonComponent(getTimestamp(), getPaymentPlanVariant(), getattributeId(),
				getattributeValue());

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

}
