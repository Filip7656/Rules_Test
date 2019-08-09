package roles.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class JsonController {
	private static int getTimestamp() {
		return 32123;

	}

	private static String getPaymentPlanVariant() {
		return "aaa";

	}

	private static List<String> getattributeId() {
		List<String> attributeId = new ArrayList<String>();
		attributeId.add("one");
		attributeId.add("two");
		attributeId.add("three");
		attributeId.add("onfe");
		attributeId.add("tgwo");
		attributeId.add("tbhree");
		attributeId.add("odne");
		attributeId.add("tvwo");
		attributeId.add("thtree");
		attributeId.add("odne");
		attributeId.add("twox");
		attributeId.add("thzree");
		return attributeId;

	}

	private static List<String> getattributeValue() {
		List<String> attributeValue = new ArrayList<String>();
		attributeValue.add("1");
		attributeValue.add("1.1");
		attributeValue.add("aa1");
		attributeValue.add("1s");
		attributeValue.add("1d.1");
		attributeValue.add("aea1");
		attributeValue.add("31");
		attributeValue.add("13.1");
		attributeValue.add("a3a1");
		attributeValue.add("13");
		attributeValue.add("13.1");
		attributeValue.add("a3a1");
		return attributeValue;

	}

	public static String buildJson() {
		JsonComponent json = new JsonComponent(getTimestamp(), getPaymentPlanVariant(), getattributeId(),
				getattributeValue());

		JSONObject object = new JSONObject();

		for (int i = 0; i < json.getListSize(); i++) {
			object.put(json.getAttributeId(i), json.getAttributeValue(i));
		}

		String jsonString = new JSONObject().put("timestamp", json.getTimestamp())
				.put("paymentPlanVariant", json.getPaymentPlanVariant()).put("attributes", object).toString();

		System.out.println(jsonString);
		return jsonString;
	}

}
