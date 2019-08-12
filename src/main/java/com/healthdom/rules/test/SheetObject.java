package com.healthdom.rules.test;

import java.util.List;

public class SheetObject {

	private List<String> attributeNames;
	private List<String> dataType;
	private List<Object> inputData;

	public SheetObject(List<String> attributeNames, List<String> dataType, List<Object> inputData) {
		super();
		this.attributeNames = attributeNames;
		this.dataType = dataType;
		this.inputData = inputData;
	}

	public List<String> getAttributeNames() {
		return attributeNames;
	}

	public void setAttributeNames(List<String> attributeNames) {
		this.attributeNames = attributeNames;
	}

	public List<String> getDataType() {
		return dataType;
	}

	public void setDataType(List<String> dataType) {
		this.dataType = dataType;
	}

	public List<Object> getInputData() {
		return inputData;
	}

	public void setInputData(List<Object> inputData) {
		this.inputData = inputData;
	}

}
