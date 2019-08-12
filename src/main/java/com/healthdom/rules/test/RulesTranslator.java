package com.healthdom.rules.test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RulesTranslator {

	public static List<Object> convertForApi(List<List<Object>> allData, ArrayList<Object> calculatedColumn)
			throws ParseException {
		numberApi(allData, calculatedColumn);
		genderApi(allData, calculatedColumn);
		timestampApi(allData, calculatedColumn);
		return calculatedColumn;
	}

	private static List<Object> numberApi(List<List<Object>> allData, ArrayList<Object> calculatedColumn) {
		List<Object> columnWithData = allData.get(2);
		List<Object> columnWithType = allData.get(1);

		for (int i = 0; i < columnWithData.size(); i++) {
			if (columnWithType.get(i).equals("number")) {
				calculatedColumn.add(i, columnWithData.get(i));
			} else {
				calculatedColumn.add("");

			}
		}
		return calculatedColumn;
	}

	private static List<Object> genderApi(List<List<Object>> allData, ArrayList<Object> calculatedColumn) {
		List<Object> columnWithData = allData.get(2);
		List<Object> columnWithType = allData.get(1);

		for (int i = 0; i < columnWithData.size(); i++) {
			if (columnWithType.get(i).equals("gender")) {
				if(columnWithData.get(i).equals("female")) {
					calculatedColumn.set(i, "F");
				}else if(columnWithData.get(i).equals("male")) {
					calculatedColumn.set(i, "M");
				}
			}

		}
		return columnWithType;
	}

	private static List<Object> timestampApi(List<List<Object>> allData, ArrayList<Object> calculatedColumn)
			throws ParseException {
		List<Object> columnWithData = allData.get(2);
		List<Object> columnWithType = allData.get(1);

		for (int i = 0; i < columnWithData.size(); i++) {
			if (columnWithType.get(i).equals("timestamp")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = dateFormat.parse(columnWithData.get(i).toString());
				long time = date.getTime();
				calculatedColumn.set(i, time);

			}

		}
		return columnWithType;
	}
}
