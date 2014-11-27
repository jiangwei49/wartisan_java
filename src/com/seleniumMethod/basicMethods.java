package com.seleniumMethod;

import net.sf.json.JSONObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class basicMethods {
	JSONObject basicResult  = new JSONObject();
	
	//没有Chrome，IE的iqdong程序
	public WebDriver openWeb(String webName){
		WebDriver driver = null;
		if (webName.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (webName.equals("chrom")) {
			DesiredCapabilities chromeCapabilities = DesiredCapabilities
					.chrome();
			String chromeBinary = "C:/Users/his92863/Documents/Phoebe_Files/WebDriver/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeBinary);
			driver = new ChromeDriver(chromeCapabilities);
		} else if (webName.equals("ie")) {
			DesiredCapabilities ieCapabilities = DesiredCapabilities
					.internetExplorer();
			ieCapabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			String ieBinary = "C:/Users/his92863/Documents/Phoebe_Files/WebDriver/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", ieBinary);
			driver = new InternetExplorerDriver(ieCapabilities);
		} else if (webName.equals("")) {
			System.out.println("请写入浏览器信息");
		} else {
			System.out.println("请选择firefox/chrom/ie 浏览器~");
		}

		return driver;
	}
	
	/**
	 * 打开url
	 * @param driver 是浏览器驱动固定参数
	 * @param htmlUrl 传入的要代开的页面的地址参数
	 * @return 基本的测试结果信息
	 */
	public JSONObject openUrl(WebDriver driver, String htmlUrl){
		try{
			driver.get(htmlUrl);
//			String mes = "Have open the URL: "+htmlUrl;
			basicResult.put("basicUrl", "Opened the page!");
			basicResult.put("result", "Test on the page: "+htmlUrl);
//			basicResult = "Have open the URL: "+htmlUrl;
			System.out.println(basicResult);
		}catch(Error e){
			basicResult.put("basicUrl", "Can not open the page!");
			basicResult.put("result", "Please check whether the url is right: "+htmlUrl);
//			basicResult = "Please check whether the url is right: "+htmlUrl;
			System.out.println(basicResult);
		}
		return basicResult;
	}
}
