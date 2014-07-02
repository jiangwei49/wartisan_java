package com.seleniumMethod;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class caseResult {
	 StringBuilder sb = new StringBuilder();
	/**
	 * @param String filepath
	 * filepath是保存该文件的路径。
	 * 生成保存测试结果的html文件
	 * 属于caseResult类
	 */
	public File newHtml(String filepath){
		StringBuilder path = new StringBuilder();
		path.append(filepath);//通过用户传入的路径作为保存html文件的目录
		Date todaytime = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat ttime = new SimpleDateFormat("yyyyMMddhhMMSS");
		String name = ttime.format(todaytime);// 生成当前时间作为文件名
		String folder = date.format(todaytime);// 生成当前日期作为文件夹名
		File file1 = new File(path.append("\\" + folder + "\\").toString());
		file1.mkdir();
		File file2 = new File(path.append(name + ".html").toString());
		return file2;
		
	}
	/**
	 * @param File file,String content
	 * file是建立文件类型特定参数，content是改文件中写入的内容的保存在该参数中。
	 * 将测试结果的内容写入生成好的html文件中。
	 * 属于caseResult类
	 */
	public String writeHtml(File file,String content){
		
		
		try {
			file.createNewFile();// 创建文件
			sb.append("<html><head><title>CaseResult</title></head><body>");
			
			//通过循环写入多次测试的结果，形成一个完整的测试报告。
			sb.append("<div align='center'>");
			sb.append(content);
			sb.append("</div>");
			sb.append("</body></html>");
			PrintStream printStream = new PrintStream(new FileOutputStream(
					file));
			printStream.println(sb.toString());// 将字符串写入文件
			printStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
