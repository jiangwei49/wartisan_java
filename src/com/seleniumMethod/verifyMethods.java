package com.seleniumMethod;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.methodInterface.methodInterface;

public class verifyMethods implements methodInterface{

	StringBuffer verificationErrors = new StringBuffer();
	String verifyResult = null;

	/**
	 * 测试页面的page title是否正确。 属于verifyMethods类
	 * 
	 * @param driver
	 *            是浏览器驱动固定参数
	 * @param titleText
	 *            测试頁面title內容
	 * @param url
	 *            是頁面的link地址（待定，用于写入测试结果）。
	 * @return testResult 测试结果保存参数。
	 */
	public String verifyPageTitle(WebDriver driver, String titleText) {

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
	}

	/**
	 * 0.1版本，该方法就是Update，对selenium即文本校验功能。
	 * 根据传入的浏览器类型参数以及传入的数据定位类型调用不同的定位测试方法，需要验证的verifyText文本内容进行测试。 属于verifyMethods类
	 * @param driver 是浏览器驱动固定参数
	 * @param verifyText  需要进行验证对比的文本内容
	 * @param pathType 传入的页面定位参数类型
	 * @param pathText 传入的页面对位参数
	 * @return
	 */
	public String verifyText(WebDriver driver, String verifyText ,String pathType, String pathText) {
		int waitTime = 10000;
		WebElement webElement = new findElementAndWait().isByElementDisplayed(driver, pathType, pathText, waitTime);
		if (verifyText != "" && verifyText != null) {
			try{
				assertEquals(verifyText,webElement.getText());
				verifyResult = "Case Success! Found the text: "+verifyText+" in pathType: "+pathType+" : " +pathText+ '\n';
				System.out.println(verifyResult);
			} catch (Exception e) {
				verifyResult = "Case Fail! There are something wrong with the pathType: "+pathType+ " : " + pathText + '\n';
				System.out.println(verifyResult);
			}catch(AssertionError e){
				verifyResult = "Case Fail! The text: " +verifyText+" does not match in pathType: " + pathType + ": "+pathText+'\n';
				System.out.println(verifyResult);
			}
		}else{
			verifyResult = "The text given to verify is null or balnk. The pathType is: " + pathType + " : " + pathText + '\n';
			System.out.println(verifyResult);
		}
		return verifyResult;
	}

	/**
	 * 根据用户传入的数据，测试页面元素的对应属性值与比对值是否一致。现阶段必须进行精准对位，属于verifyMethods类。
	 * @param driver 浏览器驱动固定参数
	 * @param verifyText 需要比对的验证的数据
	 * @param elementProperty 需要验证的页面元素的属性值
	 * @param pathType 传入的页面中元素的定位的参数类型
	 * @param pathText 传入的页面中的元素的定位参数
	 * @return
	 */
	public String verifyElementProperty(WebDriver driver, String verifyText ,String elementProperty, String pathType, String pathText) {
		int waitTime = 2000;
		WebElement webElement = new findElementAndWait().isByElementDisplayed(driver, pathType, pathText, waitTime);
		//添加一个方法进行多属性匹配~
		try{
			assertEquals(verifyText,webElement.getAttribute(elementProperty));
//			System.out.println("Case Success! The element property in the page is :"+webElement.getAttribute(elementProperty));
			verifyResult = "Case Success! The "+ elementProperty +" for element in pathType "+pathType+" : " +pathText+ " is right"+'\n';
			System.out.println(verifyResult);
		} catch (Exception e) {
			verifyResult = "Case Fail! Something wrong with the test, maybe the page not load in or path is wrong. The pathText: "+pathText+ '\n';
			System.out.println(verifyResult);
		}catch(AssertionError e){
			verifyResult = "Case Fail! The "+ elementProperty +" for element is not match in pathType: " + pathType + " : " + pathText+'\n';
			System.out.println(verifyResult);
		}
		return verifyResult;
	}
	
	/**
	 * 验证某个定位下的元素是否存在
	 * @param driver
	 * @param pathType
	 * @param pathText
	 * @return 返回的是布尔型变量。
	 */
	public String verifyElementExist(WebDriver driver, String pathType, String pathText){
		int waitTime = 2000;
		WebElement webElement = new findElementAndWait().isByElementDisplayed(driver, pathType, pathText, waitTime);
		if(webElement == null){
			verifyResult = "The Element which want to find is not exist in the "+pathType+" : "+pathText+'\n';
			System.out.println(verifyResult);
			
		}else{
			verifyResult = "The Element which want to find is exist in the "+pathType+" : "+pathText+'\n';
			System.out.println(verifyResult);
		}
		return verifyResult;
	}
}
