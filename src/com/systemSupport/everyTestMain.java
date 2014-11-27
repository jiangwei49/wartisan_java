package com.systemSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.openqa.selenium.WebDriver;

import com.caseResult.reportEmail;
import com.formatJson.formatJsonData;
import com.mongoDB.OperateMongoDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.seleniumMethod.basicMethods;
import com.seleniumMethod.readXml;
import com.seleniumMethod.seleniumStartHtml;

public class everyTestMain {
	
//编写过程中用来测试每步骤运行是否成功的方法。
	public static void main(String[] args) throws Exception{
		
		//读取xml文件，连接数据库
		Map<String, String> xmlInformation = new HashMap<String, String>();
		xmlInformation = new readXml().readXml("suibian.xml", "Connect");
		DBCollection OPcollection = new OperateMongoDB().connectMongoDB(xmlInformation);
		
		//不提供关键字，查询整个数据库
		String[] dataJsonArray = new OperateMongoDB().operaMongoDB(OPcollection, "", "");
		
		WebDriver driver = new seleniumStartHtml().openWeb("Firefox");
		
		for(int n = 0 ; n < dataJsonArray.length; n++){
			
			
			List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
			
			rsList = formatJsonData.formatJsonOnce(dataJsonArray[n]);
			
			String startPage = new formatJsonData().unicodeToChar(rsList.get(0).get("startPage"));
			
//			String basicResult = new basicMethods().openUrl(driver, startPage);
			
			
			
			String caseId = rsList.get(0).get("_id");
			String userName = rsList.get(0).get("updatedBy");
			
			
			String oneCase = rsList.get(0).get("actions");
			
			JSONArray actionArray = new formatJsonData().formatJsonTwice(oneCase);
			
			String[] caseResult = new String[actionArray.size()+1];
			System.err.println(actionArray.size());
			String[] caseResultEmail = new String[1];
			
//			caseResult[0] = basicResult;
//			caseResultEmail[0] += "<div align='left'>"+basicResult+"</div>";
			
			//匹配测试用例
			for(int m = 1 ; m < actionArray.size()+1 ; m++){
				JSONObject actionStepObj = actionArray.getJSONObject(m-1);
				caseResult[m] += new matchTestCase().actionsStep(actionStepObj,driver,m);
				System.out.println("the caseResult[m] is "+m+" "+caseResult[m]);
				caseResultEmail[0] +=  "<div align='left'>"+caseResult[m]+"</div>";
			}
			
			//update the result to the DB
			BasicDBObject resultObject = new BasicDBObject();
			resultObject = resultObject.append("caseId", caseId);
			
			xmlInformation = new readXml().readXml("suibian.xml", "Save");
			DBCollection saveCollection = new OperateMongoDB().connectMongoDB(xmlInformation);
//			new OperateMongoDB().saveDataDB(saveCollection, resultObject, caseResult);
			
			//report the reslut to the user
			//only for test need change and add the email.
			String from = "his92863@adobe.com";
			String to = "his92863@adobe.com";
			xmlInformation = new readXml().readXml("suibian.xml", "ReportEmail");
			reportEmail report = new reportEmail();
			report.sendMail(from, to, xmlInformation.get("emailTitle"), caseResultEmail[0]);
		}
		
		
		
	}


}
