package com.healthdom.rules.test.components;

import java.util.List;

public class AttributesModel implements AppModel {
	private List<Object> attributes;
	private List<Object> dataType;

	// =================================================================
	// constructors
	// =================================================================
	public AttributesModel(List<Object> attributes, List<Object> dataType) {
		super();
		this.attributes = attributes;
		this.dataType = dataType;
	}

	// =================================================================
	// getters, setters
	// =================================================================
	public List<Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Object> attributes) {
		this.attributes = attributes;
	}

	public List<Object> getDataType() {
		return dataType;
	}

	public void setDataType(List<Object> dataType) {
		this.dataType = dataType;
	}

}
