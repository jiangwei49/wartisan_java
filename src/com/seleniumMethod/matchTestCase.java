package com.seleniumMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class matchTestCase {
		/**
		 * @param String[] casetype
		 * String[] casetype是传入的测试用例的类型标识参数。
		 * 该方法通过传入的case类型参数来匹配调用不同的case进行拼装主方法中的测试用例。
	     * 属于matchTestCase类
		 * @return 
		 * @throws Exception 
	     */
	//该方法通过传入的case类型参数来匹配调用不同的case进行拼装主方法中的测试用例。
	public Map<String, String[]> matchTestCase(List<Map<String, String>> rsList) throws Exception{
		
		WebDriver driver = new seleniumStartHtml().openWeb("Firefox");
		
//		List<Map<String, String>> rsList = new readJson().dataToList(jsonData);
		
		String[] TestResult = new String[rsList.size()];
		String[] emailContent = new String[1];
		
		if(driver !=null){
			TestResult[1] ="Have open the WebDriver: "+driver;
		}
				
		for(int i=2; i<rsList.size(); i++ ){
			System.out.println("the rsList from 2 is:"+rsList.get(i));
			
//			TestResult[i] +="The step "+ rsList.get(i).get("step")+" result :";
			
//			emailContent[0] +="The step "+ rsList.get(i).get("step")+" result :";
			
			TestResult[i] = "";
			
			switch(rsList.get(i).get("action")){
				case "openurl":
					System.out.println(rsList.get(i).get("action"));
					TestResult[i] += new basicMethods().openUrl(driver, rsList.get(i).get("verifyText"));
					emailContent[0] += "<div align='center'>"+TestResult[i]+"</div>";
					break;
				case "update":
					System.out.println(rsList.get(i).get("action")+rsList.get(i).get("pathText"));
					TestResult[i] += new verifyMethods().verifyText(driver, rsList.get(i).get("verifyText"), rsList.get(i).get("pathType"), rsList.get(i).get("pathText"));
					emailContent[0] += "<div align='center'>"+TestResult[i]+"</div>";
					break;
				case "updateProperty":
					TestResult[i] += new verifyMethods().verifyElementProperty(driver, rsList.get(i).get("verifyText"), rsList.get(i).get("elementProperty"),rsList.get(i).get("pathType"), rsList.get(i).get("pathText"));
					emailContent[0] += "<div align='center'>"+TestResult[i]+"</div>";
					break;
				case "clickElement":
					int waitTime = 5000;
					TestResult[i] += new clickMethods().clickElement(driver, rsList.get(i).get("pathType"), rsList.get(i).get("pathText"), waitTime);
					emailContent[0] += "<div align='center'>"+TestResult[i]+"</div>";
					break;
				default:
					TestResult[i] += "Can not find this function, please conect the administrator of Wartisan. Thank you for your understanding.";
					emailContent[0] += "<div align='center'>"+TestResult[i]+"</div>";
			}
			
			
		}
		Map<String, String[]> CaseTotalResult =new HashMap<String,String[]>();
		CaseTotalResult.put("TestResult",TestResult);
		CaseTotalResult.put("emailContent", emailContent);
		
		
	return CaseTotalResult;
	}

}
