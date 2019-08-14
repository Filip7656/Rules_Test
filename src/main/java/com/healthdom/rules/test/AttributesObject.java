package com.healthdom.rules.test;

import java.util.List;

public class AttributesObject {
	private List<Object> attributes;
	private List<Object> dataType;

	public AttributesObject(List<Object> attributes, List<Object> dataType) {
		super();
		this.attributes = attributes;
		this.dataType = dataType;
	}

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
