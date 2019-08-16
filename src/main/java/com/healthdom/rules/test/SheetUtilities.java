package com.healthdom.rules.test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

public class SheetUtilities {
	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	final static String spreadsheetId = "1HXcELfXPL8O-Yo-IzfR68FfHL3-PUOZiiOyG5BpzBgk";
	final static String range = "TESTS!C:EE";

	public static List<List<Object>> openSheet() throws IOException, GeneralSecurityException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				GoogleApiConnect.getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).setMajorDimension("COLUMNS")
				.execute();

		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		}

		return values;
	}

	public static AttributesObject parseSheetToObjectAttributes(List<List<Object>> wholeSheet) {
		AttributesObject atributes = new AttributesObject(wholeSheet.get(0), wholeSheet.get(1));
		return atributes;
	}

	public static List<SheetObject> parseSheetToObject(List<List<Object>> wholeSheet) {
		List<SheetObject> data = new ArrayList<SheetObject>();

		for (int i = 0; i < wholeSheet.size(); i++) {
			if (!wholeSheet.get(i).isEmpty() && wholeSheet.get(i).get(0).equals("TEST DATA")) {
				SheetObject testData = new SheetObject(wholeSheet.get(i), wholeSheet.get(i + 1), wholeSheet.get(i + 4),
						wholeSheet.get(i + 3), wholeSheet.get(i + 2));
				data.add(testData);
			}
		}
		return data;
	}

	public static List<Object> getColumn(int columnNumber, List<List<Object>> columns) {
		List<Object> choosenColumn = columns.get(columnNumber);
		return choosenColumn;

	}

	private static Map<Object, Object> mapAttributes(List<Object> attributesColumn, List<Object> valuesColumn) {
		Map<Object, Object> attributesMap = new HashMap<Object, Object>();

		for (int i = 0; i < attributesColumn.size(); i++) {
			attributesMap.put(attributesColumn.get(i), valuesColumn.get(i));
		}
		return attributesMap;

	}

	public static void saveSheet(List<List<Object>> allData) throws GeneralSecurityException, IOException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				GoogleApiConnect.getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();

		ValueRange body = new ValueRange().setValues(allData).setMajorDimension("COLUMNS");
		UpdateValuesResponse result = service.spreadsheets().values().update(spreadsheetId, range, body)
				.setValueInputOption("RAW").execute();
		System.out.printf("%d cells updated.", result.getUpdatedCells());

	}

	public static List<SheetObject> compareResults(List<SheetObject> testObjects) {
		for (int numerOfObject = 0; numerOfObject < testObjects.size(); numerOfObject++) {
			for (int numerOfResult = 0; numerOfResult < testObjects.get(numerOfObject).getRulesResult()
					.size(); numerOfResult++) {
				if (testObjects.get(numerOfObject).getRulesResult().size() == testObjects.get(numerOfObject)
						.getResponseFromApi().size()
						&& !testObjects.get(numerOfObject).getResponseFromApi().get(numerOfResult).toString()
								.isEmpty()) {
					if (testObjects.get(numerOfObject).getRulesResult().get(numerOfResult)
							.equals(testObjects.get(numerOfObject).getResponseFromApi().get(numerOfResult))) {
						testObjects.get(numerOfObject).getComparisionResult().set(numerOfResult, "OK");
					} else {
						testObjects.get(numerOfObject).getComparisionResult().set(numerOfResult, "#ERROR!");
					}
				}
			}
		}

		return testObjects;
	}

	public static void saveObjectsToSheet(List<SheetObject> testObjects, List<List<Object>> wholeSheet)
			throws IOException, GeneralSecurityException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

		for (int i = -4; i < testObjects.size(); i++) {
			wholeSheet.set(i + 6, (List<Object>) testObjects.get(i + 4));
		}

		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				GoogleApiConnect.getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();

		ValueRange body = new ValueRange().setValues(wholeSheet).setMajorDimension("COLUMNS");
		UpdateValuesResponse result = service.spreadsheets().values().update(spreadsheetId, range, body)
				.setValueInputOption("USER_ENTERED").execute();
		System.out.printf("%d cells updated.", result.getUpdatedCells());
	}

}
