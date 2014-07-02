package com.mongoDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class TestCaseResult {

	/**
	 * 将case运行结果存储到数据库中
	 * @param collection
	 * @param resultData
	 * @param caseId
	 * @return
	 */
	public String saveCaseResult(DBCollection collection, List<String> resultData ,String caseId){
		
		BasicDBObject resultObject = new BasicDBObject();
		resultObject = resultObject.append("testCaseID", caseId);

		ArrayList resultArray = new ArrayList();

		for (int i = 0; i < resultData.size(); i++) {
			BasicDBObject res = new BasicDBObject();
			res = res.append("resultID", i + 1).append("reslut",
					resultData.get(i));
			resultArray.add(res);
		}
		resultObject = resultObject.append("caseResult", resultArray);
//		new OperateMongoDB().saveDataDB(collection, resultObject);
		
		return null;
		
	}
	/**
	 * 获取系统当前时间
	 * @return
	 */
	public String nowDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式 ,设置日期格式
		System.out.println(dateFormat.format(new Date()));// new Date()为获取当前系统时间
		String nowDate = dateFormat.format(new Date());
		return nowDate;
	}
}
