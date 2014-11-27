package com.seleniumMethod;

import static org.junit.Assert.assertEquals;
import net.sf.json.JSONObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.systemSupport.givenConfig;

public class verifyMethods {

	StringBuffer verificationErrors = new StringBuffer();
//	String[]  verifyResult = new String[2];
	JSONObject verifyResult = new JSONObject();
	private String pathType = givenConfig.pathType;
//	private WebDriver driver = givenConfig.driver;
	private int waitTime = 10000;
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
	public JSONObject verifyPageTitle(WebDriver driver, String titleText, int n) {

		try {
			assertEquals(titleText, driver.getTitle());
			verifyResult.put("stepId", "verify step_"+n);
			verifyResult.put("status", "Case Success!");
			verifyResult.put("result", "The page title is: " + titleText+".");
//			verifyResult[0] ="Case Success!";
//			verifyResult[1] = "The page title is: " + titleText+ '\n' + '\r';
		} catch (Exception e) {
			verificationErrors.append(e.toString());
			verifyResult.put("stepId", "verify step_"+n);
			verifyResult.put("status", "Case Fail!");
			verifyResult.put("result", "There is something wrong with the testing, the page not load properly.");
//			verifyResult[0] ="Case Fail!";
//			verifyResult[1] = "There is something wrong with the testing, the page not load properly." + '\n' + '\r';
		}catch(AssertionError e){
			verifyResult.put("status", "Case Fail!");
			verifyResult.put("result", "The page title is not: " + titleText+".");
//			verifyResult[0] ="Case Fail!";
//			verifyResult[1] = "The page title is not: " + titleText+ '\n';
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
	public JSONObject verifyText(WebElement webElement, String verifyText, String pathText,int n) {
		
		if (verifyText != "" && verifyText != null) {
			try{
				assertEquals(verifyText,webElement.getText());
				verifyResult.put("stepId", "verify step_"+n);
				verifyResult.put("status", "Case Success!");
				verifyResult.put("result", "Found the text: "+verifyText+" in the path: "+ pathText +".");
//				verifyResult[0] ="Case Success!";
//				verifyResult[1] = "Found the text: "+verifyText+" in the path: "+ pathText + '\n' + '\r';
				System.err.println(verifyResult);
			} catch (Exception e) {
				verifyResult.put("stepId", "verify step_"+n);
				verifyResult.put("status", "Case Fail!");
				verifyResult.put("result", "There is something wrong with the path or page: "+  pathText +".");
//				verifyResult[0] ="Case Fail!";
//				verifyResult[1] = "There is something wrong with the path or page: "+  pathText + '\n' + '\r';
				System.err.println(verifyResult);
			}catch(AssertionError e){
				verifyResult.put("stepId", "verify step_"+n);
				verifyResult.put("status", "Case Fail!");
				verifyResult.put("result", "The text: " +verifyText+" does not match in the page text: " + webElement.getText()+" in the path: "+pathText+".");
//				verifyResult[0] ="Case Fail!";
//				verifyResult[1] = "The text: " +verifyText+" does not match in the page text: " + webElement.getText()+" in the path: "+pathText+'\n' + '\r';
				System.err.println(verifyResult);
			}
		}else{
//			verifyResult[0] ="Case Fail!";
//			verifyResult[1] = "The text given to verify is null or balnk. The path is: " +  pathText + '\n' + '\r';
			System.err.println(verifyResult);
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
	
	public JSONObject verifyElementProperty(WebElement webElement, String verifyText ,String elementProperty, String pathText,int n) {
		//添加一个方法进行多属性匹配~
		try{
			assertEquals(verifyText,webElement.getAttribute(elementProperty));
			verifyResult.put("stepId", "verify step_"+n);
			verifyResult.put("status", "Case Success!");
			verifyResult.put("result", "The element property in path: "+ pathText +" is match given property: " +elementProperty+".");
//			System.out.println("Case Success! The element property in the page is :"+webElement.getAttribute(elementProperty));
//			verifyResult[0] ="Case Success!";
//			verifyResult[1] = "The element property in path: "+ pathText +" is match given property: " +elementProperty+ '\n' + '\r';
			System.err.println(verifyResult);
		} catch (Exception e) {
			verifyResult.put("stepId", "verify step_"+n);
			verifyResult.put("status", "Case Fail!");
			verifyResult.put("result", "There is something wrong with the test, maybe the page not load in or path is wrong. The pathText is: "+pathText+".");
//			verifyResult[0] ="Case Fail!";
//			verifyResult[1] = "There is something wrong with the test, maybe the page not load in or path is wrong. The pathText is: "+pathText+ '\n' + '\r';
			System.err.println(verifyResult);
		}catch(AssertionError e){
			verifyResult.put("stepId", "verify step_"+n);
			verifyResult.put("status", "Case Fail!");
			verifyResult.put("result", "The element property in path: "+ pathText+" is not match given elementProperty: "+elementProperty+".");
//			verifyResult[0] ="Case Fail!";
//			verifyResult[1] = "The element property in path: "+ pathText+" is not match given elementProperty: "+elementProperty+ '\n' + '\r';
			System.err.println(verifyResult);
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
	public JSONObject verifyElementExist(WebElement webElement,  String pathText, int n){
		if(webElement == null){
			verifyResult.put("stepId", "verify step_"+n);
			verifyResult.put("status", "Case Success!");
			verifyResult.put("result", "The Element which want to find is not exist in the path: "+ pathText+".");
//			verifyResult[0] ="Case Success!";
//			verifyResult[1] = "The Element which want to find is not exist in the path: "+ pathText+ '\n' + '\r';
			System.err.println(verifyResult);
			
		}else{
			verifyResult.put("stepId", "verify step_"+n);
			verifyResult.put("status", "Case Fail!");
			verifyResult.put("result", "The Element which want to find is exist in the path: "+pathText+".");
//			verifyResult[0] ="Case Fail!";
//			verifyResult[1] = "The Element which want to find is exist in the path: "+pathText+ '\n' + '\r';
			System.err.println(verifyResult);
		}
		return verifyResult;
	}
}
