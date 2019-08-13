package com.healthdom.rules.test;

import java.io.IOException;
import java.security.GeneralSecurityException;
<<<<<<< HEAD
=======
import java.util.Arrays;
import java.util.HashMap;
>>>>>>> master
import java.util.List;
import java.util.Map;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
<<<<<<< HEAD
=======
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
>>>>>>> master
import com.google.api.services.sheets.v4.model.ValueRange;

public class SheetUtilities {
	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
<<<<<<< HEAD

	public static List<List<Object>> openSheet() throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1HXcELfXPL8O-Yo-IzfR68FfHL3-PUOZiiOyG5BpzBgk";
		final String range = "TESTS!C:H";
=======
	final static String spreadsheetId = "1HXcELfXPL8O-Yo-IzfR68FfHL3-PUOZiiOyG5BpzBgk";
	final static String range = "TESTS!C:U";

	public static List<List<Object>> openSheet() throws IOException, GeneralSecurityException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

>>>>>>> master

		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				GoogleApiConnect.getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).setMajorDimension("COLUMNS")
				.execute();

		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
<<<<<<< HEAD
		} else {

		}
=======
		}

>>>>>>> master
		return values;
	}

	public static List<Object> getColumn(int columnNumber, List<List<Object>> columns) {
		List<Object> choosenColumn = columns.get(columnNumber);
		return choosenColumn;
	}

<<<<<<< HEAD
=======
	public static Map<Object, Object> mapAttributes(List<Object> attributesColumn, List<Object> valuesColumn) {
		Map<Object, Object> attributesMap = new HashMap<Object, Object>();

		for (int i = 0; i < attributesColumn.size(); i++) {
			attributesMap.put(attributesColumn.get(i), valuesColumn.get(i));
		}
		return attributesMap;

	}
	public static void saveSheet(List<Object> columnToSave,int columnNumber, List<List<Object>> allData) throws GeneralSecurityException, IOException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

		allData.set(columnNumber, columnToSave);
		
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				GoogleApiConnect.getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		
		ValueRange body = new ValueRange()
		        .setValues(allData).setMajorDimension("COLUMNS");
		UpdateValuesResponse result =
		        service.spreadsheets().values().update(spreadsheetId, range, body)
		                .setValueInputOption("USER_ENTERED")
		                .execute();
		System.out.printf("%d cells updated.", result.getUpdatedCells());
		
		
		
	}
	
>>>>>>> master
}
