package com.seleniumMethod;

import org.openqa.selenium.WebDriver;

public class basicMethods {
	String basicResult  = null;
	
	/**
	 * 打开url
	 * @param driver 是浏览器驱动固定参数
	 * @param htmlUrl 传入的要代开的页面的地址参数
	 * @return 基本的测试结果信息
	 */
	public String openUrl(WebDriver driver, String htmlUrl){
		try{
			driver.get(htmlUrl);
			basicResult = "Have open the URL: "+htmlUrl;
			System.out.println(basicResult);
		}catch(Error e){
			basicResult = "Please check whether the url is right: "+htmlUrl;
			System.out.println(basicResult);
		}
		return basicResult;
	}
}
