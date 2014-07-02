package com.formatJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class readJson {
	
	/**
	 * 处理传入的List中的json数据并将json数据进行解析
	 * 1. 分解list中的数据
	 * 2. 解析json数据
	 * 3. 将json数据传入selenium方法中进行test方法的调用
	 */
	/**
	 * 1. 分解list中的数据
	 */
	private static JSONObject listToJson(List<JSONObject> listObject){
		JSONObject caseOneJson = new JSONObject();
		for(int m=0; m<listObject.size();m++){
			caseOneJson = listObject.get(m);
			System.out.println(caseOneJson);
		}
		return null;
		
	}
	/**
	 * 该方法通过循环将jsonArray数据解析成对应的map数据并存储在List中
	 * @param jsonArray  需要解析的jsonArray
	 * @param rsList   需要返回的存储解析好的json数据的List
	 * @param localid  该组解析好的数据存储在List中的位置定义
	 * @return  存储了解析好数据的List
	 */
	private static List<Map<String, String>> arrayToMap(JSONArray jsonArray, List<Map<String,String>> rsList, int localid){
		for (int i = 0; i < jsonArray.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				String value = jsonObject.get(key).toString();
				map.put(key, value);
				System.out.println("the map.get(key) is :"+map.get(key));
			}
			rsList.add(localid,map);
		}
		return rsList;
	}
	
	/**
	 * 根据已知的数据结构，解析json数据成为几段，并通过调用arrayToMap方法，将数据解析并存储在List中返回使用。
	 * @param jsonData  数据库中读出的json数据String
	 * @return  存储了解析好数据的List
	 * @throws Exception
	 */
	
	public static List<Map<String,String>> dataToList(String jsonData) throws Exception {
		
		
		List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
		
		JSONArray totalArray = JSONArray.fromObject("["+jsonData+"]");
		JSONObject totalObj = JSONObject.fromObject(jsonData);
		
		JSONArray durationArray = totalObj.getJSONArray("duration");
		JSONArray durationJsonArray = JSONArray.fromObject(durationArray.get(0));
		
		JSONArray stepsArray = totalObj.getJSONArray("steps");
		
		rsList = arrayToMap(totalArray, rsList, rsList.size());
		
		rsList = arrayToMap(durationJsonArray, rsList, rsList.size());
		
		for(int i = 0; i<stepsArray.size();i++){
			JSONArray stepsJsonArray = JSONArray.fromObject(stepsArray.get(i));
			rsList = arrayToMap(stepsJsonArray, rsList, rsList.size());
			System.out.println("i get the action in the rslist is : "+rsList.get(i+2).get("action"));
		}
		return rsList;
	}
	
	
	
	
	
	/**
	 * json字符串转map map中的数据可以通过get方法来调用
	 * 
	 * @param dataJson
	 *            传入的json数据信息，应该是从数据库中读到的信息。
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> jsonStringToList(String dataJson)
			throws Exception {

		JSONArray arry = JSONArray.fromObject(dataJson);

		System.out.println("json字符串内容如下" + arry);

		List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();

		for (int i = 0; i < arry.size(); i++) {
			JSONObject jsonObject = arry.getJSONObject(i);
			Map<String, String> map = new HashMap<String, String>();
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				String value = jsonObject.get(key).toString();
				map.put(key, value);
			}
			rsList.add(map);
		}

		return rsList;
	}
	/**
	 * 通过处理，将得到的json数组转换成map数组并将数据传出
	 * 
	 * @param dataJsonArray
	 *            通过查询得到的数据结构。
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map[] formatJsonArray(String[] dataJsonArray) throws Exception {

		Map[] datamap = new Map[dataJsonArray.length];

		for (int n = 0; n < dataJsonArray.length; n++) {
			System.out.println("the data json in formatJsonArray is : "+dataJsonArray[n]);
			List<Map<String, String>> list = jsonStringToList(dataJsonArray[n]);

			System.out.println("json字符串成map");
			for (Map<String, String> m : list) {
				System.out.println(m);
				datamap[n] = m;
			}
		}
		return datamap;
	}

	/**
	 * 反解析，将map数据解析成json数据
	 * @param mapData
	 * @return
	 * @throws Exception
	 */
	public JSONObject formatMapJson(Map mapData) throws Exception {
		// 仅对一条数据进行格式转换
		System.out.println("map转换成json字符串");
		// 转换成Array则数据项中存在[]
		JSONArray jsonArray = JSONArray.fromObject(mapData);
		// 转换成Object则数据项中不存在[]
		JSONObject jsonArray1 = JSONObject.fromObject(mapData);
		return jsonArray1;

	}


}