package com.healthdom.rules.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.healthdom.rules.test.components.AttributesObject;
import com.healthdom.rules.test.components.SheetObject;

@SpringBootApplication
public class App {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		List<List<Object>> wholeSheet = SheetUtilities.openSheet();
		List<SheetObject> testObjects = SheetUtilities.parseSheetToObject(wholeSheet);
		AttributesObject attributes = SheetUtilities.parseSheetToObjectAttributes(wholeSheet);
		List<List<Object>> attributesList= ApiService.createAttributesListToSave(testObjects, attributes);
		
		RulesTranslator.convertForApi(testObjects, attributes);
		SheetUtilities.compareResults(testObjects);
	//	SheetUtilities.saveSheet(wholeSheet);
		SheetUtilities.saveObjectsToSheet(testObjects, wholeSheet);
		//SheetUtilities.saveSheet(ApiService.createAttributesListToSave(), 5, wholeSheet);

	}
}