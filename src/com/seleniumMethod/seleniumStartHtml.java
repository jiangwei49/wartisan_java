package com.seleniumMethod;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.methodInterface.startHtml;


public class seleniumStartHtml implements startHtml {

	/**
	 * webName webName是出入用户需要调用的浏览器类型名称。 根据用户选择，传入参数，启动相应的浏览器。
	 * 属于seleniumMethod类
	 */
	public WebDriver openWeb(String webName) {
		WebDriver driver = null;
		// if (webName != null){
		if (webName.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (webName.equals("chrom")) {
			DesiredCapabilities chromeCapabilities = DesiredCapabilities
					.chrome();
			String chromeBinary = "C:/Users/his92863/Documents/Phoebe_Files/WebDriver/chromedriver.exe";
			// if (chromeBinary == null || chromeBinary.equals("")) {
			System.setProperty("webdriver.chrome.driver", chromeBinary);
			// }
			driver = new ChromeDriver(chromeCapabilities);
		} else if (webName.equals("ie")) {
			DesiredCapabilities ieCapabilities = DesiredCapabilities
					.internetExplorer();
			ieCapabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			String ieBinary = "C:/Users/his92863/Documents/Phoebe_Files/WebDriver/chromedriver.exe";
			// if (ieBinary == null || ieBinary.equals("")) {
			System.setProperty("webdriver.chrome.driver", ieBinary);
			// }
			driver = new InternetExplorerDriver(ieCapabilities);
		} else if (webName.equals("")) {
			System.out.println("请写入浏览器信息");
		} else {
			System.out.println("请选择firefox/chrom/ie 浏览器~");
		}

		// }
		return driver;
	}

	// 退出浏览器
	public String shutWeb(WebDriver driver) {

		driver.quit();
		return null;
	}

	// 获取测试用urls
	public String getUrl(WebDriver driver, String urls) {
		if (urls != "") {
			driver.get(urls);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

}
