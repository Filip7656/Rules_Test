package com.healthdom.rules.test;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.healthdom.rules.test.components.AttributesModel;
import com.healthdom.rules.test.components.SheetObjectModel;

@SpringBootApplication
@SuppressWarnings("ucd")
public class App {

	public static void main(String[] args) throws Exception {
		List<List<Object>> wholeSheet = SheetUtilities.openSheet();
		List<SheetObjectModel> testObjects = SheetUtilities.parseSheetToObject(wholeSheet);
		AttributesModel attributes = SheetUtilities.parseSheetToObjectAttributes(wholeSheet);
		ApiService.addResponseFromApitoTestObjects(testObjects, attributes);
		RulesTranslator.calculateValuesForApi(testObjects, attributes);
		SheetUtilities.compareResults(testObjects);
		System.out.println(wholeSheet.get(7).toString());
		SheetUtilities.saveObjectsToSheet(testObjects, wholeSheet);
	}
}