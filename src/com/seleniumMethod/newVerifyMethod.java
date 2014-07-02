package com.seleniumMethod;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class newVerifyMethod {
	public String verifyMethod(WebDriver driver, JSONObject oneAction){
		
		String pathType = "xPath";
		int waitTime = 2000;
//		String actionType = oneAction.getString("description");
		String caseProperty="";
		String caseCondition ="";
		String caseValue = "";
		for(int n = 1; n<oneAction.length()+1;n++){
			caseProperty = oneAction.getJSONObject("step_"+n).getString("property");
			caseCondition = oneAction.getJSONObject("step_"+n).getString("condition");
			caseValue = oneAction.getJSONObject("step_"+n).getString("value");
		}
//		if(actionType.equals("Recording")){
//			for(int n= 0; n<oneAction.length();n++){
//				
//			}
//		}else if(actionType.equals("verify")){
			String pathText = oneAction.getString("path");
			
//		}
		
		
		
		WebElement webElement = new findElementAndWait().isByElementDisplayed(driver, pathType, pathText, waitTime);
		
		try {
			assertEquals(titleText, driver.getTitle());
			verifyResult = "page title is right!" + '\n';
			System.out.println("Page title is right! The title is: "
					+ titleText);
		} catch (Exception e) {
			verificationErrors.append(e.toString());
			verifyResult = "There is something wrong with the test, the page not load properly." + '\n';
			System.out.println(verifyResult);
		}catch(AssertionError e){
			verifyResult = "The page title is not :" + titleText+ '\n';
			System.out.println(verifyResult);
		}
		return verifyResult;
		return null;
		
	}
}
