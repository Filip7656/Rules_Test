package com.healthdom.rules.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) throws Exception {
		List <Object> calculatedColumn = new ArrayList<Object>();
		ApiService.POST();
		List<List<Object>> wholeSheet = SheetUtilities.openSheet();
		List<SheetObject> testObjects = SheetUtilities.parseSheetToObject(wholeSheet);
		AttributesObject attributes = SheetUtilities.parseSheetToObjectAttributes(wholeSheet);
		RulesTranslator.convertForApi(testObjects, attributes);
		SheetUtilities.compareResults(testObjects);
		SheetUtilities.saveSheet(calculatedColumn, 3, wholeSheet);
		System.exit(0);
	}
}