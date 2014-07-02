package com.systemSupport;



import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.formatJson.formatJsonData;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;



	public class everyTestMain {
		public  static void main(String[] args) throws UnknownHostException, MongoException, JSONException {
 			//连接数据库
			Map<String, String> dbInformation = new HashMap<String, String>();
			dbInformation.put("dbserverip","10.162.139.9");
			dbInformation.put("dbport","27017");
			dbInformation.put("dbname", "casetest");
			dbInformation.put("collectionname", "ffcase");
			DBCollection collection = new com.mongoDB.OperateMongoDB().connectMongoDB(dbInformation);
			System.out.println("success connect the DB");
			//查询数据库，并将数据以json的形式存在List中
			JSONObject filedata = new JSONObject();
			
			List<JSONObject> listObject = new ArrayList<JSONObject>();
			
			BasicDBObject query = new BasicDBObject("", "");
			DBCursor allCursor = collection.find();
			String[] dataJsonArray = new String[allCursor.count()];
			for (int n = 0; allCursor.hasNext(); n++) {
				dataJsonArray[n] = JSON.serialize(allCursor.next());
				
				filedata = new JSONObject(dataJsonArray[n]);
				
				listObject.add(n, filedata);
			}
			System.out.println("the DB data total number : "+listObject.size());
//			for(int m=0; m<listObject.size();m++){
//				filedata = listObject.get(m);
				System.out.println("in the for : "+filedata);
//				System.out.println(filedata.getJSONObject("duration"));
//				System.out.println(filedata.getJSONArray("actions"));
//				System.out.println("the josn action size: "+filedata.getJSONArray("actions").length());
//				System.out.println(filedata.getJSONArray("actions").getJSONObject(0));
//				System.out.println(filedata.getJSONArray("actions").getJSONObject(0).getString("description"));
//				System.out.println(filedata.getJSONArray("actions").getJSONObject(0).getJSONObject("step_"+1));
//			}
			List<JSONObject> caseObject = new ArrayList<JSONObject>();
			for (int n = 0; n<listObject.size();n++){
				JSONObject oneCase = listObject.get(n);
				System.out.println("the one case data is : "+oneCase);
				
				caseObject.add(0,oneCase);
				caseObject.add(1,oneCase.getJSONObject("duration"));
				System.out.println("teh caseObject 1 :"+caseObject.get(1));
				
				int actionLength = oneCase.getJSONArray("actions").length();
				System.out.println(actionLength);
				
				List<JSONArray> caseList = new ArrayList<JSONArray>(); 
				for(int m= 0; m<actionLength; m++){
					System.out.println("teh action is : "+filedata.getJSONArray("actions"));
					JSONObject oneAction =  oneCase.getJSONArray("actions").getJSONObject(m);
					
					String stepType = oneAction.getString("description");

					System.out.println("the one step is : "+oneAction);
					caseObject.add(m+2,oneAction);
					System.out.println(stepType);
//					需要处理单条数据
					
					if(stepType.equals("Recording")){
						for(int k = 1; k<oneAction.length()-1;k++){
							JSONObject stepCase = oneAction.getJSONObject("step_"+k);
							System.out.println("the stepCase in step "+k+" is :"+stepCase);
							String oneProperty = stepCase.getString("property");
							stepCase.put("property", oneProperty);
							System.out.println(oneProperty);
							String validValue = new formatJsonData().slipData(oneProperty);
							System.out.println(validValue);
							String unicode = stepCase.getString("value");
							String charData=new everyTestMain().unicodeToChar(unicode);
							System.out.println(charData);
							stepCase.put("value", charData);
							System.out.println(stepCase);
						}
					}else if(stepType.equals("verify")){
						for(int h = 1;h<oneAction.length()-1;h++){
							JSONObject stepCase = oneAction.getJSONObject("step_"+h);
							System.out.println("the stepCase in step "+h+" is :"+stepCase);
							String oneProperty = stepCase.getString("property");
							stepCase.put("property", oneProperty);
							System.out.println(oneProperty);
							String validValue = new formatJsonData().slipData(oneProperty);
							System.out.println(validValue);
							String unicode = stepCase.getString("value");
							String charData=new everyTestMain().unicodeToChar(unicode);
							System.out.println(charData);
							stepCase.put("value", charData);
							System.out.println(stepCase);
						}
					}
					System.out.println("final action is : "+filedata.getJSONArray("actions"));
					String caseProperty="";
					String caseCondition="";
					String caseValue="";
					for(int k = 1; k<oneAction.length()-1;k++){
						caseProperty = oneAction.getJSONObject("step_"+k).getString("property");
						caseCondition = oneAction.getJSONObject("step_"+k).getString("condition");
						caseValue = oneAction.getJSONObject("step_"+k).getString("value");
						System.out.println("the value is "+caseProperty+" next: "+caseCondition+" next: "+ caseValue);
					}
				}
			}
			}    
//			String a="to input adb";
//			String b = "linproperty_target";
//			String type1 = a.substring(9);
//			System.out.println(type1);
//			String type = b.substring(b.indexOf("_") + 1);
//			int test = a.indexOf("linproperty_");
//			System.out.println(test);
//			test = b.indexOf("linproperty_");
//			System.out.println(test);
//		}
				public String unicodeToChar(String valueData){
					
					String charData="";
//					int includeU = valueData.indexOf("%u");
					charData=  StringEscapeUtils.unescapeHtml(valueData);
					charData =URLDecoder.decode(charData);
					return charData;
				}
	}
