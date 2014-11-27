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

public class caseRun extends java.util.TimerTask {

	@Override
	public void run() {
		// / TODO Auto-generated method stub
		try {
			new caseRun().caseRun();
		} catch (Exception e) {
			System.out.println("something wrong with the run method.");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("run once~!");
	}

	public void caseRun() throws Exception {

		// 读取xml文件，连接数据库
		Map<String, String> xmlInformation = new HashMap<String, String>();
		xmlInformation = new readXml().readXml("suibian.xml", "Connect");
		DBCollection OPcollection = new OperateMongoDB()
				.connectMongoDB(xmlInformation);

		// 不提供关键字，查询整个数据库
		String[] dataJsonArray = new OperateMongoDB().operaMongoDB(
				OPcollection, "", "");

		WebDriver driver = new basicMethods().openWeb("Firefox");
		for (int n = 0; n < dataJsonArray.length; n++) {

			int l = 1;

			List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();

			rsList = formatJsonData.formatJsonOnce(dataJsonArray[n]);

			String caseStatus = rsList.get(0).get("status");

			if (caseStatus.equals("active")) {

				String startPage = new formatJsonData().unicodeToChar(rsList.get(0).get("startPage"));

				JSONObject basicResult = new basicMethods().openUrl(driver, startPage);

				String caseId = rsList.get(0).get("_id");
				String userName = rsList.get(0).get("updatedBy");
				String caseTitle = rsList.get(0).get("title");
				// only for test need change and add the email.

				String oneCase = rsList.get(0).get("actions");

				JSONArray actionArray = new formatJsonData().formatJsonTwice(oneCase);

				// String[] caseResult = new String[actionArray.size()*2+2];
				// ArrayList<String> caseResult = new ArrayList<String>();
				JSONArray caseResult = new JSONArray();
				// String emailContent = "";

				basicResult.put("caseTitle", caseTitle);
				caseResult.add(0, basicResult);

				// 匹配测试用例
				JSONObject result = new JSONObject();
				for (int m = 1; m < actionArray.size() + 1; m++) {

					JSONObject actionStepObj = actionArray.getJSONObject(m - 1);
					result = new matchTestCase().actionsStep(actionStepObj,driver,m);
					caseResult.add(l, result);
					l++;
				}
				// System.out.println(caseResultEmail.length
				// +" ling yige shuxing "+ caseResult);

				// update the result to the DB
				BasicDBObject resultObject = new BasicDBObject();
				String nowDate = new systemTime().nowDate();
				resultObject = resultObject.append("caseId", caseId);
				resultObject = resultObject.append("updatedBy", userName);
				resultObject = resultObject.append("caseRunTime", nowDate);

				xmlInformation = new readXml().readXml("suibian.xml", "Save");
				DBCollection saveCollection = new OperateMongoDB().connectMongoDB(xmlInformation);
				new OperateMongoDB().saveDataDB(saveCollection, resultObject,caseResult);

				// report the reslut to the user
				// email content
				String caseResultEmail = caseResult.toString();
				// String emailContent =
				// formatJsonData.formatEmailContent(caseResultEmail);
				String emailContent = formatJsonData.formatEmailContent(caseResult);

				xmlInformation = new readXml().readXml("suibian.xml","ReportEmail");
				String from = xmlInformation.get("sendEmail");
				String emailTitle = xmlInformation.get("emailTitle");
				// to 應該對應到郵件地址。。？？？？
				// String to = rsList.get(0).get("updatedBy");
				// String to ="his92863@adobe.com";
				// reportEmail report = new reportEmail();
				new reportEmail().sendMail(from, userName, emailTitle,emailContent);
			} else {
				System.out.println("跳过一个case。");
			}
		}

	}
}
