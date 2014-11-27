package com.systemSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class systemTime {
	/**
	 * 获取系统当前时间
	 * @return
	 */
	public String nowDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式 ,设置日期格式
		String nowDate = dateFormat.format(new Date());
		return nowDate;
	}
}
