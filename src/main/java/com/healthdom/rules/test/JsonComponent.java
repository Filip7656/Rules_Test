package com.healthdom.rules.test;

import java.util.List;

public class JsonComponent {

	private String timestamp;
	private String paymentPlanVariant;
	private List<Object> attributeId;
	private List<Object> attributeValue;

	public JsonComponent(String timestamp, String paymentPlanVariant, List<Object> attributeId,
			List<Object> attributeValue) {
		super();
		this.timestamp = timestamp;
		this.paymentPlanVariant = paymentPlanVariant;
		this.attributeId = attributeId;
		this.attributeValue = attributeValue;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPaymentPlanVariant() {
		return paymentPlanVariant;
	}

	public void setPaymentPlanVariant(String paymentPlanVariant) {
		this.paymentPlanVariant = paymentPlanVariant;
	}

	public Object getAttributeId(int s) {
		return attributeId.get(s);
	}

	public List<Object> getAttributeIds() {
		return attributeId;
	}

	public void setAttributeId(List<Object> attributeId) {
		this.attributeId = attributeId;
	}

	public Object getAttributeValue(int s) {
		return attributeValue.get(s);
	}

	public void setAttributeValue(List<Object> attributeValue) {
		this.attributeValue = attributeValue;
	}

	public int getListSize() {
		return attributeId.size();
	}

}
