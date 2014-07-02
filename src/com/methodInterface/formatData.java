package com.methodInterface;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public interface formatData {

	public String slipData(String valueProperty);	//对特殊值的截取处理
	public String caseformat(List<JSONObject> listObject) throws JSONException;
	public String unicodeToChar(String valueData);
	public JSONObject formatStep(JSONObject oneAction) throws JSONException;
}
