package com.caseResult;

	import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class reportEmail {
 
	 public reportEmail() {
	
	 }

	 public void sendMail( String from, String to, String title , String content) {
		 try {
			   System.out.println("正在发送邮件，请稍侯...");
			   Properties p = System.getProperties();
			   p.put("mail.transport.protocol", "smtp");
			   p.put("mail.smtp.host", "Inner-relay-3.eur.adobe.com");
			
			   Session session = Session.getDefaultInstance(p);
			
			   Message mailMessage = new MimeMessage(session);
			   mailMessage.setFrom(new InternetAddress(from));
			   mailMessage.setRecipient(Message.RecipientType.TO,
			     new InternetAddress(to));
			   mailMessage.setSubject(title);
			   //如果按照这句发的话，html标记在接受过程中会被编译，接收者受到的就不是超链接了
//			   mailMessage.setText(content);
			   mailMessage.setContent(content, "text/html;charset=utf-8");
			   // 想收到超链接形式就要这样设置。。后面的String一定要这样写哦，有好处的，防止乱码！！
			
			   Transport.send(mailMessage);
			   System.out.println("邮件发送完毕!");
			} catch (Exception ex) {
			   ex.printStackTrace();
		  }
	 }
}