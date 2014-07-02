package com.methodInterface;

import org.openqa.selenium.WebDriver;

public interface startHtml {
	
	public WebDriver openWeb(String webName);
	public String shutWeb(WebDriver driver);
	public String getUrl(WebDriver driver, String urls);
	
}
