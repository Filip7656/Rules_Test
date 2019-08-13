package com.healthdom.rules.test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) throws IOException, GeneralSecurityException, ParseException {
	List<List<Object>> allData =SheetUtilities.openSheet();
	List<Object> calculatedColumn = allData.get(3);	
	SheetUtilities.saveSheet(RulesTranslator.convertForApi(allData, calculatedColumn), 3,allData);

	}
}