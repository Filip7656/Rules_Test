package com.healthdom.rules.test;

import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) throws Exception {
		// List<Object> calculatedColumn = new ArrayList<Object>();
		// List<List<Object>> wholeSheet = SheetUtilities.openSheet();
		// RulesTranslator.convertForApi(wholeSheet, calculatedColumn);
		// .saveSheet(calculatedColumn, 3, wholeSheet);
		// JsonController.mapJson();
		Map<String, Integer> map = new HashMap<>();
		map.put("a", 4);
		map.put("c", 6);
		map.put("b", 2);
		Object[] a = map.entrySet().toArray();
		Arrays.sort(a, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Map.Entry<String, Integer>) o2).getValue()
						.compareTo(((Map.Entry<String, Integer>) o1).getValue());
			}
		});
		Map<Integer, String> test = new HashMap<>();
		for (Object e : a) {
			test.put(((Map.Entry<String, Integer>) e).getValue(), (((Map.Entry<String, Integer>) e).getKey()));
		}
		System.out.println(test.values());
		
		
		RulesTranslator.responseFromApi();

	}
}