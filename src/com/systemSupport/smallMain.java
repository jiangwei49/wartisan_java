package com.systemSupport;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.commons.lang.StringEscapeUtils;


public class smallMain {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws UnsupportedEncodingException  {
//		String a="to input adb";
//		String b = "linproperty_target";
//		String c ="to click";
		
//		String type1 = a.substring(9);
//		System.out.println(type1);
//		String type = b.substring(b.indexOf("_") + 1);
//		int test = a.indexOf("linproperty_");
//		System.out.println(test);
//		test = b.indexOf("linproperty_");
//		System.out.println(test);
		
//		String t = c;
//		int include = t.indexOf("linproperty_");
//		if(t.equals("to click")){
//			t = "click";
//		}else if(include == -1){
//			t = t.substring(9);
//		}else if(include == 0){
//			t = t.substring(t.indexOf("_") + 1);
//		}
//		System.out.println(t);
		
		String c = "找到相关新闻";
		 String s = "%u79D1%u6280%u5BFC%u822A";
		 String s5 = "%E8%A6%81%E9%97%BB";
		 String s1 = "&#27494;&#26415;145%2C298";
		 String s2 = "145%2C298";
		 String s3 = "http%3A%2F%2Fmobile.sina.com.cn%2F";
		 String s4 = "%2Fhtml%5B1%5D%2Fbody%5B1%5D%2Fdiv%5B6%5D%2Fdiv%5B8%5D%2Fdiv%5B2%5D%2Fdiv%5B1%5D%2Fdiv%5B2%5D%2Fh1%5B1%5D%2Fa%5B1%5D";
//		  String test = new String(s.getBytes("UTF-8"),Charset.forName("UTF-8"));
		  
		  String test=  StringEscapeUtils.unescapeHtml(s3);
//		 String test = URLDecoder.decode(s1,"UTF-8");
		   test =URLDecoder.decode(test);
//		 test = StringEscapeUtils.unescapeXml(test);
//		 test = StringEscapeUtils.escapeHtml(c);
//		  test = StringEscapeUtils.escapeHtml(s3);
		 System.out.println(test);
			 
//		  String ss = s.replace("%u", "\\u");
//
//		  String str = ss.split(" ")[0];
		  
//		  String ss = s.replace("%","");
//		  
//		  String[] arr = ss.split("u");
//		  
//		  String text = "";
//		  
//		  for(int i = 1; i < arr.length; i++){
//		      int hexVal = Integer.parseInt(arr[i], 16);
//		      text += (char)hexVal;
//		  }
//		  System.out.println(text);
		  
		  
//		  String s1 = "&#27494;&#26415;";
////		  char[] test= new char[]{25991, 23398, 29702};
//		  
//		  
//		  String s2 = s1.replace("&#", "");
////		  String ar3= s2.replace(";", ", ");
//		  String[] ar3 = s2.split(";");
////		  char[] test = new char[]{};
//		  String ls = "";
//		  
//		  for(int m=0;m<ar3.length; m++){
//			  int ar5 = Integer.parseInt(ar3[m]);
//			  char c=(char)ar5;
//			  String test = String.valueOf(c);
//			  ls += test;
////			  test[m]= c;
//			  
//		  }
//		  System.out.println(ls);
		  
		 
//		  ar3= ar3.substring(0, ar3.length()-2); 
		  
//		  String[] ar2 = s2.split(";");
//		  int arr = 27991;
//		  char[] test = ar3.toCharArray();
//		  
//		 char c =  (char)27991;
//		 
//		  for(int m=0; m<ar2.length;m++){
//			  System.out.println("the ar2 is :"+ar2[m]);
//			  test = ar2[m].toCharArray();
//			  System.out.println("the test is :"+test);
//			  
////			  char cha = ar2[m].charAt(2);
////			  test[m]= cha;
//		  }
//		  	for(int m= 0; m<ar2.length; m++){
//		  		test[m]=ar2[m].toCharArray();
//		  	}
//		  String text2 ="";
//			  System.out.println(test.length);
//		  System.err.println(new String(test));
			  
//		  System.out.println(text2);
//			int includeU = valueData.indexOf("%u");
//			int includeC = valueData.indexOf("&#");
//			int includeH = valueData.indexOf("%3A");
//			int includeS = valueData.indexOf("%5B");
//			
//			if(includeU == 0){
//				
//				String removeU = valueData.replace("%", "");
//				String[] dataString = removeU.split("u"); 
//				for(int i = 1; i<dataString.length;i++){
//					int hexVal = Integer.parseInt(dataString[i],16);
//					charData += (char)hexVal;
//				}
//			}else if(includeC == 0){
//				String removeC = valueData.replace("&#", "");
//				String[] vauleNum = removeC.split(";");
//				 for(int m=0;m<vauleNum.length; m++){
//					  int hexVal = Integer.parseInt(vauleNum[m]);
//					  char c=(char)hexVal;
//					  String singleChar = String.valueOf(c);
//					  charData += singleChar;
//				  }
//			}else if(includeH == 4){
//				charData = valueData.replace("%3A", ":");
//			}else if(includeS == 5){
//				charData = valueData.replace("%5B", "[");
//				charData = valueData.replace("%5D", "]");
//			}else{
//				charData = valueData;
//			}
	}
}
