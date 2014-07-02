package com.methodInterface;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface methodInterface{
	
	public String verifyText(WebDriver driver, String verifyText ,String pathType, String pathText);
	public String verifyPageTitle(WebDriver driver, String titleText);
	public String verifyElementProperty(WebDriver driver, String verifyText ,String elementProperty, String pathType, String pathText);
	public String verifyElementExist(WebDriver driver, String pathType, String pathText);
//	public String veryTextpresent(String text,WebDriver driver);
}
