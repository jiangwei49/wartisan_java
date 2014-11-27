package com.seleniumMethod;

import net.sf.json.JSONObject;

import org.openqa.selenium.WebElement;

import com.systemSupport.givenConfig;

public class clickMethods {
	StringBuffer verificationErrors = new StringBuffer();
	JSONObject clickResult = new JSONObject();
	JSONObject inputResult = new JSONObject();

	/**
	 * 通过元素定位参数类型来调用参数定位程序，定位后点击该元素并返回结果。
	 * @param driver 浏览器驱动固定参数
	 * @param pathType  传入的定位参数类型
	 * @param pathText  传入的定位参数内容
	 * @return  返回测试后的结果信息
	 */
	private String pathType = givenConfig.pathType;
	
	public JSONObject clickElement(WebElement webElement, String pathText, int n) {
		try{
			webElement.click();
			clickResult.put("stepId", n);
			clickResult.put("status", "Case Success!");
			clickResult.put("result", "Have clicked the element in the path: " +pathText+".");
//			clickResult[0] ="Case Success!";
//			clickResult[1] = "Have clicked the element in the path: " +pathText+ '\n' + '\r';
//			System.err.println(clickResult);
		} catch (Exception e) {
			clickResult.put("stepId", n);
			clickResult.put("status", "Case Fail!");
			clickResult.put("result", "Can not find the element in the path: " + pathText +".");
//			clickResult[0] ="Case Fail!";
//			clickResult[1] = "Can not find the element in the path: " + pathText + '\n' + '\r';
//			System.err.println(clickResult);
		}
		return clickResult;
	}
	
	public JSONObject inputElement(WebElement webElement, String inputText, int n){
	    try{
	    	webElement.sendKeys(inputText);
	    	inputResult.put("stepId", n);
	    	inputResult.put("status", "Case Success!");
	    	inputResult.put("result", "Successful input the word: "+inputText+".");
//	    	inputResult[0] ="Case Success";
//	    	inputResult[1] = "Successful input the word: "+inputText+ '\n' + '\r';
//	    	System.err.println(inputResult);
	    } catch(Exception e){
	    	inputResult.put("stepId", n);
	    	inputResult.put("status", "Case Fail!");
	    	inputResult.put("result", "Fail input the word: "+inputText+".");
//	    	inputResult[0] ="Case Fail!";
//	    	inputResult[1] = "Fail input the word: "+inputText+ '\n' + '\r';
//	    	System.err.println(inputResult);
	    }
		return inputResult; 
		
		 
//	      selenium.waitForPageToLoad("50000"); 
	}
}
