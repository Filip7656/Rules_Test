package com.healthdom.rules.test.components;

import org.apache.http.HttpStatus;

public class ResponseModel implements AppModel{
	public HttpStatus testStatus;
	public String error;
	public String logError;

	// =================================================================
	// getters, setters
	// =================================================================
	public HttpStatus getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(HttpStatus ok) {
		this.testStatus = ok;
	}

	public String getError() {
		return error;
	}

	public String getLogError() {
		return logError;
	}

	public void setLogError(String logs) {
		logError = logs;
	}

	public void setError(String error) {
		this.error = error;
	}

}