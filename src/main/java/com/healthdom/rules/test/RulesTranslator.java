package com.healthdom.rules.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RulesTranslator {

	public static List<SheetObject> convertForApi(List<SheetObject> testObjects, AttributesObject attributes)
			throws ParseException {

		numberApi(testObjects, attributes);
		genderApi(testObjects, attributes);
		timestampApi(testObjects, attributes);
		booleanApi(testObjects, attributes);
		ethnicityApi(testObjects, attributes);
		yesNoNaApi(testObjects, attributes);
		return testObjects;
	}

	private static List<SheetObject> numberApi(List<SheetObject> testObjects, AttributesObject attributes) {
		for (int i = 0; i < testObjects.size(); i++) {
			for (int y = 0; y < attributes.getDataType().size(); y++) {
				if (attributes.getDataType().get(y).equals("number"))
					testObjects.get(i).getRulesResult().set(y, testObjects.get(i).getInputData().get(y));
			}
		}
		return testObjects;
	}

	private static List<SheetObject> genderApi(List<SheetObject> testObjects, AttributesObject attributes) {
		for (int i = 0; i < testObjects.size(); i++) {
			for (int y = 0; y < attributes.getDataType().size(); y++) {
				if (attributes.getDataType().get(y).equals("gender"))
					if (testObjects.get(i).getInputData().get(y).equals("female")) {
						testObjects.get(i).getRulesResult().set(y, "F");
					} else if (testObjects.get(i).getInputData().get(y).equals("male")) {
						testObjects.get(i).getRulesResult().set(y, "M");
					}
			}
		}
		return testObjects;
	}

	private static List<SheetObject> timestampApi(List<SheetObject> testObjects, AttributesObject attributes)
			throws ParseException {

		for (int i = 0; i < testObjects.size(); i++) {
			for (int y = 0; y < attributes.getDataType().size(); y++) {
				if (attributes.getDataType().get(y).equals("timestamp") && !testObjects.get(i).getInputData().get(y).toString().isEmpty()) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = dateFormat.parse(testObjects.get(i).getInputData().get(y).toString());
					long time = date.getTime();
					testObjects.get(i).getRulesResult().set(y, time);
				}
			}
		}
		return testObjects;
	}

	private static List<SheetObject> booleanApi(List<SheetObject> testObjects, AttributesObject attributes) {
		for (int i = 0; i < testObjects.size(); i++) {
			for (int y = 0; y < attributes.getDataType().size(); y++) {
				if (attributes.getDataType().get(y).equals("boolean")) {
					if (testObjects.get(i).getInputData().get(y).equals("TRUE")) {
						testObjects.get(i).getRulesResult().set(y, "1");
					} else if (testObjects.get(i).getInputData().get(y).equals("FALSE")) {
						testObjects.get(i).getRulesResult().set(y, "0");
					}
				}
			}
		}
		return testObjects;
	}

	private static List<SheetObject> ethnicityApi(List<SheetObject> testObjects, AttributesObject attributes) {

		for (int i = 0; i < testObjects.size(); i++) {
			for (int y = 0; y < attributes.getDataType().size(); y++) {

				if (attributes.getDataType().get(y).equals("ethnicity")) {

					if (testObjects.get(i).getInputData().get(y).equals("whites")) {
						testObjects.get(i).getRulesResult().set(y, "0");
					} else if (testObjects.get(i).getInputData().get(y).equals("hispanics")) {
						testObjects.get(i).getRulesResult().set(y, "1");
					} else if (testObjects.get(i).getInputData().get(y).equals("asian")) {
						testObjects.get(i).getRulesResult().set(y, "3");
					} else if (testObjects.get(i).getInputData().get(y).equals("black")) {
						testObjects.get(i).getRulesResult().set(y, "4");
					} else if (testObjects.get(i).getInputData().get(y).equals("american_indians")) {
						testObjects.get(i).getRulesResult().set(y, "5");
					} else if (testObjects.get(i).getInputData().get(y).equals("pacific_islander")) {
						testObjects.get(i).getRulesResult().set(y, "9");
					} else if (testObjects.get(i).getInputData().get(y).equals("other")) {
						testObjects.get(i).getRulesResult().set(y, "7");
					}
				}
			}
		}
		return testObjects;
	}

	private static List<SheetObject> yesNoNaApi(List<SheetObject> testObjects, AttributesObject attributes) {

		for (int i = 0; i < testObjects.size(); i++) {
			for (int y = 0; y < attributes.getDataType().size(); y++) {
				if (attributes.getDataType().get(y).equals("yes_no_na")) {	
					if (testObjects.get(i).getInputData().get(y).equals("yes")) {
						testObjects.get(i).getRulesResult().set(y, "'Y'");
					} else if (testObjects.get(i).getInputData().get(y).equals("no")) {
						testObjects.get(i).getRulesResult().set(y, "'N'");
					}  else if (testObjects.get(i).getInputData().get(y).equals("n/a")) {
						testObjects.get(i).getRulesResult().set(y, "'NA'");
					}
				}
			}
		}
		return testObjects;
	}

}
