package com.healthdom.rules.test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

public class SheetUtilities {
	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	public static List<List<Object>> openSheet() throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1HXcELfXPL8O-Yo-IzfR68FfHL3-PUOZiiOyG5BpzBgk";
		final String range = "TESTS!C:H";

		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				GoogleApiConnect.getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).setMajorDimension("COLUMNS")
				.execute();

		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {
			
		}
		return values;
	}

	public List<Object> getCollumn(int columnNumber, List<List<Object>> columns) {
		 List<Object> choosenColumn = columns.get(columnNumber);
		 return choosenColumn;	
	}
	
	
}
