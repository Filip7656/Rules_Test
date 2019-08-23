package com.healthdom.rules.test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.healthdom.rules.test.components.AttributesModel;
import com.healthdom.rules.test.components.SheetObjectModel;

class SheetUtilities {
	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private final static String spreadsheetId = "1HXcELfXPL8O-Yo-IzfR68FfHL3-PUOZiiOyG5BpzBgk";
	private final static String range = "TESTS!C:EE";
	private static Logger LOG = LoggerFactory.getLogger(SheetUtilities.class);

	static List<List<Object>> openSheet() throws IOException, GeneralSecurityException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		// ---------------
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				GoogleApiConnect.getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		// ---------------
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).setMajorDimension("COLUMNS")
				.execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			LOG.error("No data found");
		}
		return values;
	}

	static AttributesModel parseSheetToObjectAttributes(List<List<Object>> wholeSheet) {
		AttributesModel atributes = new AttributesModel(wholeSheet.get(0), wholeSheet.get(1));
		return atributes;
	}

	static List<SheetObjectModel> parseSheetToObject(List<List<Object>> wholeSheet) {
		List<SheetObjectModel> data = new ArrayList<SheetObjectModel>();
		// ---------------
		for (int i = 0; i < wholeSheet.size(); i++) {
			if (!wholeSheet.get(i).isEmpty() && wholeSheet.get(i).get(0).equals("TEST DATA")) {
				SheetObjectModel testData = new SheetObjectModel(wholeSheet.get(i), wholeSheet.get(i + 1),
						wholeSheet.get(i + 4), wholeSheet.get(i + 3), wholeSheet.get(i + 2));
				data.add(testData);
			}
		}
		// ---------------
		return data;
	}

	static List<SheetObjectModel> compareResults(List<SheetObjectModel> testObjects) {
		for (int numerOfObject = 0; numerOfObject < testObjects.size(); numerOfObject++) {
			compareResults(testObjects, numerOfObject);
		}
		return testObjects;
	}

	public static void saveObjectsToSheet(List<SheetObjectModel> testObjects, List<List<Object>> wholeSheet)
			throws IOException, GeneralSecurityException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		int size = (testObjects.size() * 6) - 8;
		for (int i = 0; i < size; i += 6) {
			int numberOfObject = i / 6;
			wholeSheet.set(i +5, testObjects.get(numberOfObject).getResponseFromApi());

		}
		// ---------------
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				GoogleApiConnect.getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange body = new ValueRange().setValues(wholeSheet).setMajorDimension("COLUMNS");
		UpdateValuesResponse result = service.spreadsheets().values().update(spreadsheetId, range, body)
				.setValueInputOption("USER_ENTERED").execute();
		LOG.debug("Updated cells: " + result.getUpdatedCells());
		// ---------------
	}

	private static void compareResults(List<SheetObjectModel> testObjects, int numerOfObject) {
		for (int numerOfResult = 0; numerOfResult < testObjects.get(numerOfObject).getRulesResult()
				.size(); numerOfResult++) {
			if (testObjects.get(numerOfObject).getRulesResult().size() == testObjects.get(numerOfObject)
					.getResponseFromApi().size()
					&& !testObjects.get(numerOfObject).getResponseFromApi().get(numerOfResult).toString().isEmpty()) {
				if (testObjects.get(numerOfObject).getRulesResult().get(numerOfResult)
						.equals(testObjects.get(numerOfObject).getResponseFromApi().get(numerOfResult))) {
					testObjects.get(numerOfObject).getComparisionResult().set(numerOfResult, "OK");
				} else {
					testObjects.get(numerOfObject).getComparisionResult().set(numerOfResult, "#ERROR!");
				}
			}
		}
	}

}
