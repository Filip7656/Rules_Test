package com.healthdom.rules.test;

import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) throws Exception {
		List<Object> calculatedColumn = new ArrayList<Object>();
		List<List<Object>> wholeSheet = SheetUtilities.openSheet();
		RulesTranslator.convertForApi(wholeSheet, calculatedColumn);
		SheetUtilities.saveSheet(calculatedColumn, 3, wholeSheet);
		JsonController.mapJson();
	}
}