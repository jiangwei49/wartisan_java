package com.systemSupport;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.formatJson.formatJsonData;
import com.seleniumMethod.clickMethods;
import com.seleniumMethod.findElementAndWait;
import com.seleniumMethod.verifyMethods;


public class matchTestCase {
	
//	private WebDriver driver = givenConfig.driver;
	private String pathType = givenConfig.pathType;
	private JSONArray verifyResult = new JSONArray();
	private JSONObject caseRe = new JSONObject();
	
	public JSONObject actionsStep(JSONObject actionStepObj, WebDriver driver, int s){
		// object may need change to String
		Object actionsDes = actionStepObj.get("description");
		JSONObject caseResult = new JSONObject();
//		JSONArray caseResult = new JSONArray();
		// need different method for selenium
		if(actionsDes.equals("Recording")){
			JSONArray clickResult = new JSONArray();
			//do the next thing. find the right selenium method
			for(int n =1 ; n<actionStepObj.size()-1;n++){
				
				String RecValue = (String) actionStepObj.getJSONObject("step_"+n).get("value");//
				RecValue = new formatJsonData().unicodeToChar(RecValue);
				WebElement webElement = new findElementAndWait().isByElementDisplayed(driver, RecValue);
				clickResult.add(n-1, new clickMethods().clickElement(webElement, RecValue,n));
			}
			caseResult.put("actionResult_"+s, clickResult);
			
		}else if(actionsDes.equals("verify")){
			String actionsPath = actionStepObj.get("path").toString();
			for(int n =1 ; n<actionStepObj.size()-1;n++){
				String VerContains = (String) actionStepObj.getJSONObject("step_"+n).get("contains");
				String VerValue = (String) actionStepObj.getJSONObject("step_"+n).get("value");
				String VerProperty = (String) actionStepObj.getJSONObject("step_"+n).get("property");
				
				VerValue = new formatJsonData().unicodeToChar(VerValue);
				VerProperty = new formatJsonData().unicodeToChar(VerProperty);
				
				WebElement webElement = new findElementAndWait().isByElementDisplayed(driver, actionsPath);
				
				String[] splitVaule = new String[2];
				
				if(VerProperty.startsWith("linproperty_")){
					
					splitVaule = VerProperty.split("_");
					verifyResult.add(n-1, new verifyMethods().verifyElementProperty(webElement, VerValue,splitVaule[1],actionsPath,n));
					
				}else if(VerProperty.startsWith("A_")){
					
					splitVaule = VerProperty.split("_");
					  Pattern p = Pattern.compile("(.*)/img\\[\\d*\\]$");
					  Matcher m = p.matcher(VerProperty);
					  if (m.find()) {
						  WebElement IncwebElement = new findElementAndWait().isByElementDisplayed(driver,  m.group(1));
						  verifyResult.add(n-1, new verifyMethods().verifyElementProperty(IncwebElement, VerValue,splitVaule[1],m.group(1),n));
					  } else {
						  verifyResult.add(n-1,new verifyMethods().verifyElementProperty(webElement, VerValue,splitVaule[1],actionsPath,n));
					  }
				}else if(VerProperty.startsWith("IMG_")){
					
					splitVaule = VerProperty.split("_");
					verifyResult.add(n-1, new verifyMethods().verifyElementProperty(webElement, VerValue,splitVaule[1],actionsPath,n));
					
				}else if(VerProperty.startsWith("SPAN_")){
					
					splitVaule = VerProperty.split("_");
					verifyResult.add(n-1, new verifyMethods().verifyElementProperty(webElement, VerValue,splitVaule[1],actionsPath,n));
					
				}else{
				
					switch(VerProperty){
						case "verifyText":
							verifyResult.add(n-1,new verifyMethods().verifyText(webElement, VerValue, actionsPath,n));
							break;
						case "tagname":
//							verifyResult +=
							break;
						default:
							System.out.println("do not have teh property");
							verifyResult.add(n-1,"Do not get the property for step_" +n );
							
					}
				}
			}
			caseResult.put("actionResult_"+s, verifyResult);
		}else{
			caseRe.put("status","Case Fail");
			caseRe.put("result","没有匹配的测试方法，请联系后台人员~！");
			caseResult.put("actionResult_"+s, caseRe);
		}
		
		actionStepObj.remove("path");
		actionStepObj.remove("description");
		
		return caseResult;
		
	}
	
}
