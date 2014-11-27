package com.seleniumMethod;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.systemSupport.givenConfig;

public class findElementAndWait {
	/**
	 * 隐形等待，需要driver来控制，如果等待超时抛出异常。 隐式等待(Implicit Wait)
	 * 判断传入的定位是否在页面上存在，并寻找改定位下的元素，并写入了等待时间，如果元素第一次查找不存在则等待函数传入的时间。
	 * 
	 * @param by
	 *            定位方式。
	 * @param driver
	 *            浏览器驱动固定参数
	 * @param time
	 *            等待的时间
	 * @return 网络定位的元素
	 */
	private  int  waitTime =  2000;
    private String pathType = givenConfig.pathType;
    
	public WebElement isByElementDisplayed(WebDriver driver, String pathText) {
		
		WebElement webElement = null;
		try {
			// webElement = driver.findElement(by);
			if (pathType.equals("id")) {
				webElement = driver.findElement(By.id(pathText));
			} else if (pathType.equals("xPath")) {
				webElement = driver.findElement(By.xpath(pathText));
			} else if (pathType.equals("cssPath")) {
				webElement = driver.findElement(By.cssSelector(pathText));
			}
		} catch (Exception e) {
			System.out.println("Cannot find the element locale!" + '\n');
		}
		if (webElement == null) {
			System.out.println("Waiting for: " + waitTime + '\n');
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
				System.out.println("Throw Error when Thread sleep!");
			}
			try {
				if (pathType.equals("id")) {
					webElement = driver.findElement(By.id(pathText));
				} else if (pathType.equals("xPath")) {
					webElement = driver.findElement(By.xpath(pathText));
				} else if (pathType.equals("cssPath")) {
					webElement = driver.findElement(By.cssSelector(pathText));
				}
			} catch (Exception e) {
				System.out.println("Cannot find the element locale!" + '\n');
			}
			if (webElement == null) {
				return null;
			} else {
				return webElement;
			}
		} else {
			return webElement;
		}
	}

}
