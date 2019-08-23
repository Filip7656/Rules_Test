package com.healthdom.rules.test.components;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SheetObject {

	private List<Object> inputData;
	private List<Object> rulesResult;
	private List<Object> comparisionResult;
	private List<Object> responseFromApi;
	private List<Object> expectedResult;

	public SheetObject(List<Object> inputData, List<Object> rulesResult, List<Object> comparisionResult,
			List<Object> responseFromApi, List<Object> expectedResult) {
		super();
		this.inputData = inputData;
		this.rulesResult = rulesResult;
		this.comparisionResult = comparisionResult;
		this.responseFromApi = responseFromApi;
		this.expectedResult = expectedResult;
	}

	public List<Object> getInputData() {
		return inputData;
	}

	public void setInputData(List<Object> inputData) {
		this.inputData = inputData;
	}

	public List<Object> getRulesResult() {
		return rulesResult;
	}

	public void setRulesResult(List<Object> rulesResult) {
		this.rulesResult = rulesResult;
	}

	public List<Object> getComparisionResult() {
		return comparisionResult;
	}

	public void setComparisionResult(List<Object> comparisionResult) {
		this.comparisionResult = comparisionResult;
	}

	public List<Object> getResponseFromApi() {
		return responseFromApi;
	}

	public void setResponseFromApi(List<Object> responseFromApi) {
		this.responseFromApi = responseFromApi;
	}

	public List<Object> getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(List<Object> expectedResult) {
		this.expectedResult = expectedResult;
	}


}
