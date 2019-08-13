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

<<<<<<< HEAD
	 public static void main(String[] args) throws Exception {
		 ApiService.POST();
	 }
=======
	public static void main(String[] args) throws IOException, GeneralSecurityException, ParseException {
	List<List<Object>> allData =SheetUtilities.openSheet();
	List<Object> calculatedColumn = allData.get(3);	
	SheetUtilities.saveSheet(RulesTranslator.convertForApi(allData, calculatedColumn), 3,allData);

	}
>>>>>>> master
}