package roles.json;

import java.util.List;

public class JsonComponent {

	private int timestamp;
	private String paymentPlanVariant;
	private List<String> attributeId;
	private List<String> attributeValue;

	public JsonComponent(int timestamp, String paymentPlanVariant, List<String> attributeId,
			List<String> attributeValue) {
		super();
		this.timestamp = timestamp;
		this.paymentPlanVariant = paymentPlanVariant;
		this.attributeId = attributeId;
		this.attributeValue = attributeValue;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public String getPaymentPlanVariant() {
		return paymentPlanVariant;
	}

	public void setPaymentPlanVariant(String paymentPlanVariant) {
		this.paymentPlanVariant = paymentPlanVariant;
	}

	public String getAttributeId(int s) {
		return attributeId.get(s);
	}

	public void setAttributeId(List<String> attributeId) {
		this.attributeId = attributeId;
	}

	public String getAttributeValue(int s) {
		return attributeValue.get(s);
	}

	public void setAttributeValue(List<String> attributeValue) {
		this.attributeValue = attributeValue;
	}

	public int getListSize() {
		return attributeId.size();
	}

}
