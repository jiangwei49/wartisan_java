package com.mongoDB;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import net.sf.json.JSONArray;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import com.systemSupport.systemTime;

public class OperateMongoDB {

	/**
	 * 连接数据库，读取需要的数据表。返回数据表中全部的collection
	 * 
	 * @return collection
	 * @throws UnknownHostException
	 * @throws MongoException
	 */
	public DBCollection connectMongoDB(Map<String, String> dbInformation)
			throws UnknownHostException, MongoException {
		// Map<String, String> dbInformation = new HashMap(String, String);
		// 连接数据库，参数IP和接口，可以动态传入。
		String dbserverip = dbInformation.get("dbserverip");
		int dbport = Integer.valueOf(dbInformation.get("dbport")).intValue();
		String dbname = dbInformation.get("dbname");
		String collectionname = dbInformation.get("collectionname");

		DBCollection collection = null;

		// 连接数据库服务器
		try {
			Mongo mongoserver = new Mongo(dbserverip, dbport);
			DB mongodb = mongoserver.getDB(dbname);
			collection = mongodb.getCollection(collectionname);
		} catch (Error e) {
			System.out.println("Can not connect the Mongo DB!" + '\n');
		}
		// 连接数据库
		// 连接数据库中的collection

		return collection;
	}

	/**
	 * * 读取数据表中的数据存储为json形式的list并返回list
	 * 
	 * @param collection
	 *            是连接数据库的方法返回的读取到的collecion存储的表的名称
	 * @param keyword
	 *            传入查询需要的关键字，即数据库中列名称
	 * @param keyvalue
	 *            传入查询关键字对应的数据值
	 * @return
	 * @throws UnknownHostException
	 * @throws MongoException
	 * @throws JSONException 
	 */
	public List<JSONObject> operaMongoDB(DBCollection collection, String keyword,
			String keyvalue) throws UnknownHostException, MongoException, JSONException {

		// 然后我们使用这个游标来遍历集合
		// 初始化后的数组长度需要控制和调整。 10 这个长度需要控制
		String[] dataJsonArray = new String[1];
		JSONObject caseOneJson = new JSONObject();
		List<JSONObject> listObject = new ArrayList<JSONObject>();
		
		BasicDBObject query = new BasicDBObject(keyword, keyvalue);
		// 查询数据库中的数据
		// DBObject oneObject1 = collection.findOne(query);
		DBCursor allCursor = collection.find();
		JSONArray arry = JSONArray.fromObject(dataJsonArray);
		if (keyword == "" && keyvalue == "") {

			dataJsonArray = new String[allCursor.count()];
			for (int n = 0; allCursor.hasNext(); n++) {
				dataJsonArray[n] = JSON.serialize(allCursor.next());
				caseOneJson = new JSONObject(dataJsonArray[n]);
				listObject.add(n, caseOneJson);
			}
		} else {
			query.put(keyword, keyvalue);
			allCursor = collection.find(query);
			dataJsonArray = new String[allCursor.size()];
			for (int i = 0; allCursor.hasNext(); i++) {
				dataJsonArray[i] = JSON.serialize((allCursor.next()));
				caseOneJson = new JSONObject(dataJsonArray[i]);
				
				listObject.add(i, caseOneJson);
			}
		}
		
		return listObject;
	}

	public String saveDataDB(DBCollection collection, BasicDBObject resultObject, String[] resultList) {
//		BasicDBObject resultObject = new BasicDBObject();
//		resultObject = resultObject.append("testCaseID", caseId);

		ArrayList resultArray = new ArrayList();

		for (int i = 0; i < resultList.length; i++) {
			BasicDBObject res = new BasicDBObject();
			res = res.append("resultID", i + 1).append("reslut",resultList[i]);
			resultArray.add(res);
		}
		resultObject = resultObject.append("caseResult", resultArray);
		
		System.out.println(resultObject);
		collection.insert(resultObject);

		return null;

	}

	/**
	 * 
	 * @param collection
	 * @param runtime
	 * @param caseID
	 * @param total
	 * @param state
	 * @return
	 */
	public String updateDataDB(DBCollection collection, int runtime, int caseID, int total, int state){
		String updateDataDB = null;
//		//update the date as the id=3.0
		BasicDBObject updateQuery = new BasicDBObject();
		BasicDBObject searchQuery3 = new BasicDBObject();
		String systemTime = new systemTime().nowDate();
		if(total != (runtime+1)){
			updateQuery.append("$set", new BasicDBObject().append("runtime", runtime+1).append("lastruntime", systemTime));
		}else if(total == (runtime+1)){
			updateQuery.append("$set", new BasicDBObject().append("runtime", runtime+1).append("lastruntime", systemTime).append("state", 1));
		}
		searchQuery3.append("_id", caseID);
		collection.updateMulti(searchQuery3, updateQuery);
	 //end of the update code
	return updateDataDB;
	}
}
