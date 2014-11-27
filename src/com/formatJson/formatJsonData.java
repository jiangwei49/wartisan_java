package com.formatJson;


import java.net.URLDecoder;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;

import com.seleniumMethod.basicMethods;
import com.systemSupport.matchTestCase;


public class formatJsonData {
	
	
	public static List<Map<String,String>> formatJsonOnce(String oneCaseJson) throws Exception {
		
		List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
		JSONArray totalArray = JSONArray.fromObject("["+oneCaseJson+"]");
		rsList = arrayToMap(totalArray, rsList, rsList.size());
		
		return rsList;
	}
	
	public static JSONArray  formatJsonTwice(String oneCase){
		/*
		 * if the rsList is null, need the method give a wrong message.
		 */
		JSONArray actionArray = null;

		if(oneCase.equals(null) || oneCase.equals("")){
			System.err.println("傳入的case沒有信息！");
		}else{
			actionArray = JSONArray.fromObject(oneCase);
		}
	return actionArray;
		
	}
	

	
	private static List<Map<String, String>> arrayToMap(JSONArray jsonArray, List<Map<String,String>> rsList, int localid){
		for (int i = 0; i < jsonArray.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				String value = jsonObject.get(key).toString();
				map.put(key, value);
//				System.out.println("the map.get(key) is :"+map.get(key));
			}
			rsList.add(localid,map);
		}
		return rsList;
	}
	
	//处理unicode转化成中文
	public String unicodeToChar(String valueData){
		
		String charData="";
		charData=  StringEscapeUtils.unescapeHtml(valueData);
		charData =URLDecoder.decode(charData);
		
		return charData;
	}
	
	
	/**
	 * json字符串转map map中的数据可以通过get方法来调用
	 * 
	 * @param dataJson
	 *            传入的json数据信息，应该是从数据库中读到的信息。
	 * @return
	 * @throws Exception
	 */
	
	public static String formatEmailContent(JSONArray content) {
		String emailContent = "";
		
		String startUrl = (String) content.getJSONObject(0).get("result");
		String caseTitle=(String)content.getJSONObject(0).get("caseTitle");
		emailContent +="<h2>"+caseTitle+"</h2>";
		//Sstem.out.println("output: " + startUrl);
		emailContent += "<h2>" + startUrl + "</h2>";
		
//		for(int n = 1 ; n<content.size()+1;n++){
//		JSONObject all = content.getJSONObject(1);
//		System.err.println("IS array: " + all.isArray());
		//JSONArray x = all.names();
		//System.out.println(x);
		for(int n = 1; n<content.size(); n++){
			JSONArray every_step = (JSONArray) content.getJSONObject(n).get("actionResult_"+n);//maybe there need a for
			emailContent += "<h3>"+"actionResult_"+n+"/<h3>";
			for (int k=0; k<every_step.size();k++) {
				JSONObject check_point = every_step.getJSONObject(k);
				String result1 = (String) check_point.get("stepId");
				String result2 = (String) check_point.get("status");
				String result3 = (String) check_point.get("result");
				
				emailContent += "<p>" + result1 + "</p>";
				emailContent += "<p>" + result2 + "</p>";
				emailContent += "<p>" + result3 + "</p>";
			}
		}
		return emailContent;
		
	}
//	public static String formatEmailContent(String emailContent) {
//
//		System.out.println("email字符串内容如下" + emailContent);
//		
//		emailContent = emailContent.replaceAll("\"", "&nbsp;");
//		emailContent = emailContent.replace("}]],[[{", "</div></p><br><p><div align='left'>");
//		emailContent = emailContent.replace("},[[{", "</div></p><br><p><div align='left'>");
//		
//		emailContent = emailContent.replace("}]]]", "</div></p>");
//		emailContent = emailContent.replace("}],[{", "<br>");
//		emailContent = emailContent.replace("},{", "<br>");
//		emailContent = emailContent.replace("[{", "<p><div align='left'>");
//		
//		emailContent = emailContent.replaceAll(",", "<br>");
//		
//		emailContent = emailContent.replaceAll("status", "<b>");
//		emailContent = emailContent.replaceAll("!", "</b>");
//		
//		return emailContent;
//
//		
//	}
	
}
