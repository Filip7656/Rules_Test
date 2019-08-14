package com.healthdom.rules.test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthdom.commons.helper.JsonHelper;
import com.healthdom.commons.model.user.User;

public class RulesTranslator {

	public static List<Object> convertForApi(List<List<Object>> allData, List<Object> calculatedColumn)
			throws ParseException {
		numberApi(allData, calculatedColumn);
		genderApi(allData, calculatedColumn);
		timestampApi(allData, calculatedColumn);
		booleanApi(allData, calculatedColumn);
		ethnicityApi(allData, calculatedColumn);
		yesNoNaApi(allData, calculatedColumn);
		return calculatedColumn;
	}

	private static List<Object> numberApi(List<List<Object>> allData, List<Object> calculatedColumn) {
		List<Object> columnWithData = allData.get(2);
		List<Object> columnWithType = allData.get(1);

		for (int i = 0; i < columnWithData.size(); i++) {
			if (columnWithType.get(i).equals("number")) {
				calculatedColumn.add(i, columnWithData.get(i));
			} else {
				calculatedColumn.add("");
			}
		}
		return calculatedColumn;
	}

	private static List<Object> genderApi(List<List<Object>> allData, List<Object> calculatedColumn) {
		List<Object> columnWithData = allData.get(2);
		List<Object> columnWithType = allData.get(1);

		for (int i = 0; i < columnWithData.size(); i++) {
			if (columnWithType.get(i).equals("gender")) {
				if (columnWithData.get(i).equals("female")) {
					calculatedColumn.set(i, "F");
				} else if (columnWithData.get(i).equals("male")) {
					calculatedColumn.set(i, "M");
				}
			}
		}
		return calculatedColumn;
	}

	private static List<Object> timestampApi(List<List<Object>> allData, List<Object> calculatedColumn)
			throws ParseException {
		List<Object> columnWithData = allData.get(2);
		List<Object> columnWithType = allData.get(1);

		for (int i = 0; i < columnWithData.size(); i++) {
			if (columnWithType.get(i).equals("timestamp")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = dateFormat.parse(columnWithData.get(i).toString());
				long time = date.getTime();
				calculatedColumn.set(i, time);
			}
		}
		return calculatedColumn;
	}

	private static List<Object> booleanApi(List<List<Object>> allData, List<Object> calculatedColumn)
			throws ParseException {
		List<Object> columnWithData = allData.get(2);
		List<Object> columnWithType = allData.get(1);

		for (int i = 0; i < columnWithData.size(); i++) {
			if (columnWithType.get(i).equals("boolean")) {
				if (columnWithData.get(i).equals("TRUE")) {
					calculatedColumn.set(i, "1");
				} else if (columnWithData.get(i).equals("FALSE")) {
					calculatedColumn.set(i, "0");
				}
			}
		}
		return calculatedColumn;
	}

	private static List<Object> ethnicityApi(List<List<Object>> allData, List<Object> calculatedColumn)
			throws ParseException {
		List<Object> columnWithData = allData.get(2);
		List<Object> columnWithType = allData.get(1);

		for (int i = 0; i < columnWithData.size(); i++) {
			if (columnWithType.get(i).equals("ethnicity")) {

				if (columnWithData.get(i).equals("whites")) {
					calculatedColumn.set(i, "0");
				} else if (columnWithData.get(i).equals("hispanics")) {
					calculatedColumn.set(i, "1");
				} else if (columnWithData.get(i).equals("asian")) {
					calculatedColumn.set(i, "3");
				} else if (columnWithData.get(i).equals("black")) {
					calculatedColumn.set(i, "4");
				} else if (columnWithData.get(i).equals("american_indians")) {
					calculatedColumn.set(i, "5");
				} else if (columnWithData.get(i).equals("pacific_islander")) {
					calculatedColumn.set(i, "9");
				} else if (columnWithData.get(i).equals("other")) {
					calculatedColumn.set(i, "7");
				}

			}
		}
		return calculatedColumn;
	}

	private static List<Object> yesNoNaApi(List<List<Object>> allData, List<Object> calculatedColumn)
			throws ParseException {
		List<Object> columnWithData = allData.get(2);
		List<Object> columnWithType = allData.get(1);

		for (int i = 0; i < columnWithData.size(); i++) {
			if (columnWithType.get(i).equals("yes_no_na")) {
				if (columnWithData.get(i).equals("yes")) {
					calculatedColumn.set(i, "Y");
				} else if (columnWithData.get(i).equals("no")) {
					calculatedColumn.set(i, "N");
				} else if (columnWithData.get(i).equals("n/a")) {
					calculatedColumn.set(i, "NA");
				}
			}
		}
		return calculatedColumn;
	}
	public static void responseFromApi() throws JSONException, IOException, GeneralSecurityException
	{
		User user = JsonHelper.deserialize(ApiService.POST(), User.class);
		JsonController.mapJson().containsKey(user.getAttributes().values());
		
		
		System.out.println(user.getAttributes());
		System.out.println(user.getAttributes().values());
}
}

	