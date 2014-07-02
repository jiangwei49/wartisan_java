package com.formatJson;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.methodInterface.formatData;

public class formatJsonData implements formatData{
	//对特殊值的截取处理
	public String slipData(String valueProperty) {
		
		String validValue = null;
		
		int include = valueProperty.indexOf("linproperty_");
		
		if(valueProperty.equals("to click")){
			validValue = "click";
		}else if(valueProperty.equals("verifyText")){
			validValue = "verifyText";
		}else if(include == -1){
			validValue = valueProperty.substring(9);
			validValue = unicodeToChar(validValue);
		}else if(include == 0){
			validValue = valueProperty.substring(valueProperty.indexOf("_") + 1);
		}else{
			validValue = valueProperty;
		}
		return validValue;
	}

	
	//处理unicode转化成中文
	public String unicodeToChar(String valueData){
		
		String charData="";
//		int includeU = valueData.indexOf("%u");
		charData=  StringEscapeUtils.unescapeHtml(valueData);
		charData =URLDecoder.decode(charData);
		
//		if(includeU == 0){
//			String removeU = valueData.replace("%", "");
//			String[] dataString = removeU.split("u"); 
//			for(int i = 1; i<dataString.length;i++){
//				int hexVal = Integer.parseInt(dataString[i],16);
//				charData += (char)hexVal;
//			}
//		}else{
//			charData=  StringEscapeUtils.unescapeHtml(valueData);
//			try {
//				charData = URLDecoder.decode(charData,"UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}
		return charData;
	}
	
	
	public String caseformat(List<JSONObject> listObject) throws JSONException{
		List<JSONObject> caseObject = new ArrayList<JSONObject>();
		for (int n = 0; n<listObject.size();n++){
			JSONObject oneCase = listObject.get(n);
			caseObject.add(0,oneCase);
			caseObject.add(1,oneCase.getJSONObject("duration"));
			
			int actionLength = oneCase.getJSONArray("action").length();
			for(int m= 0; m<actionLength; m++){
				JSONObject oneAction = (JSONObject) oneCase.getJSONArray("action").get(m);
				//需要处理单条数据
				oneAction = formatStep(oneAction);
				caseObject.add(m+2, oneAction);
		}
		}
		return null;
	}
	
	//通过循环读取数据进行处理 将处理好的数据放回json
	public JSONObject formatStep(JSONObject oneAction) throws JSONException{
//		caseObject.add(m+2,oneStep);
		//需要处理单条数据
		String stepType = oneAction.getJSONObject("description").toString();
		if(stepType == "Recording"){
			for(int k = 1; k<oneAction.length()+1;k++){
				JSONObject stepCase = oneAction.getJSONObject("step_"+k);
				String oneProperty = stepCase.getString("property");
				String validValue = new formatJsonData().slipData(oneProperty);
				stepCase.put("property", validValue);
				String unicode = stepCase.getString("value");
				String unicodeValue = new formatJsonData().unicodeToChar(unicode);
				stepCase.put("value", unicodeValue);
			}
		}else if(stepType.equals("verify")){
			for(int h = 1;h<oneAction.length()-1;h++){
				JSONObject stepCase = oneAction.getJSONObject("step_"+h);
				String oneProperty = stepCase.getString("property");
				String validValue = new formatJsonData().slipData(oneProperty);
				stepCase.put("property", validValue);
				String unicode = stepCase.getString("value");
				String unicodeValue = new formatJsonData().unicodeToChar(unicode);
				stepCase.put("value", unicodeValue);
			}
		}
		return oneAction;
	}
		
}

//System.out.println(filedata.getJSONObject("duration"));
//System.out.println(filedata.getJSONArray("actions"));
//System.out.println(filedata.getJSONArray("actions").getJSONObject(0));
//System.out.println(filedata.getJSONArray("actions").getJSONObject(0).getString("description"));
//System.out.println(filedata.getJSONArray("actions").getJSONObject(0).getJSONObject("step_"+1));
