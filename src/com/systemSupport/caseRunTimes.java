package com.systemSupport;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.Timer;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

//真正的主方法

	public class caseRunTimes {
		public static void main(String[] args) throws Exception {
			
			//通过定时 控制执行间隔时间
//			Timer timer = new Timer();
//			timer.schedule(new caseRun(), 1000, 3*60*1000);//在1秒后执行此任务,每次间隔3分钟,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
//			
//			while (true) {// 这个是用来停止此任务的,否则就一直循环执行此任务了
//				try {
//					int ch = System.in.read();
//					if (ch - 'c' == 0) {
//						timer.cancel();// 使用这个方法退出任务
//					}
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
			//case 通过循环控制执行次数
			for(int i= 0; i<1; i++){
			new caseRun().caseRun();
			System.out.println("have already run the code");
			}
		}
	}
	

//			Map<String, String> dbInformation = new HashMap<String, String>();
//			dbInformation.put("dbserverip","10.162.138.201");
//			dbInformation.put("dbport","27017");
//			dbInformation.put("dbname", "casetest");
//			dbInformation.put("collectionname", "ffcase");
//			DBCollection collection = new com.mongoDB.OperateMongoDB().connectMongoDB(dbInformation);
//			
//			JSONObject filedata = new JSONObject();
//			
//			List<JSONObject> listObject = new ArrayList<JSONObject>();
//			
//			BasicDBObject query = new BasicDBObject("", "");
//			DBCursor allCursor = collection.find();
//			String[] dataJsonArray = new String[allCursor.count()];
//			for (int n = 0; allCursor.hasNext(); n++) {
//				dataJsonArray[n] = JSON.serialize(allCursor.next());
//				
//				filedata = new JSONObject(dataJsonArray[n]);
//				
//				listObject.add(n, filedata);
//				
//				System.out.println("the listObject is :"+listObject.get(n).toString());
//				
//			}
//			System.out.println(listObject);
//			
//	              
//	        }  }

		//	
		
