package com.seleniumMethod;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.thoughtworks.selenium.DefaultSelenium;

public class clickMethods {
	StringBuffer verificationErrors = new StringBuffer();
	String clickResult = null;
	String inputResult = null;

	/**
	 * 通过元素定位参数类型来调用参数定位程序，定位后点击该元素并返回结果。
	 * @param driver 浏览器驱动固定参数
	 * @param pathType  传入的定位参数类型
	 * @param pathText  传入的定位参数内容
	 * @return  返回测试后的结果信息
	 */
	public String clickElement(WebDriver driver, String pathType, String pathText, int waitTime) {
		WebElement webElement = new findElementAndWait().isByElementDisplayed(driver, pathType, pathText, waitTime);
		try{
			webElement.click();
			clickResult = "Have clicked the element in the pathType"+pathType+" : " +pathText+ '\n';
			System.out.println(clickResult);
		} catch (Exception e) {
			clickResult = "Can not find the element in the pathType" + pathType + " : " + pathText + '\n';
			System.out.println(clickResult);
		}
		return clickResult;
	}
	
	public String inputElement(WebDriver driver, String pathType, String pathText, int waitTime, String inputText){
		WebElement webElement = new findElementAndWait().isByElementDisplayed(driver, pathType, pathText, waitTime);
	    try{
	    	webElement.sendKeys(inputText);
	    	System.out.println(inputResult);
	    } catch(Exception e){
	    	System.out.println(inputResult);
	    }
		return inputResult; 
		
		 
//	      selenium.waitForPageToLoad("50000"); 
	}
}
