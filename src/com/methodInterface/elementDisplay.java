package com.methodInterface;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface elementDisplay {
	public WebElement isByElementDisplayed(WebDriver driver, String pathType,
			String pathText, int waitTime);
}
