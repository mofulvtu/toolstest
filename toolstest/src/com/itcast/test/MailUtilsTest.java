package com.itcast.test;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.junit.Test;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

/**
 * 测试MailUtils，作用是发邮件
 * 底层依赖的是javamail:mail.jar、activation.jar
 * @author 刚刚
 *
 */
public class MailUtilsTest {
	/**
	 * 发邮件
	 * @throws Exception 
	 * @throws MessagingException 
	 */
	@Test
	public void send() throws MessagingException, Exception{
		/*
		 * 1.登陆邮件服务器
		 * MailUtils.createSession(服务器地址，登陆名，密码);
		 * 2.创建邮件对象
		 *   发件人
		 *   收件人
		 *   主题
		 *   正文
		 * 3.发送
		 * 需要第一步的得到的session，和第二步的邮件对象
		 */
		Session session = MailUtils.createSession("smtp.163.com", "15764343296", "gang12365");
		
		Mail mail = new Mail("15764343296@163.com", "1172727371@qq.com","测试邮件第二封","<a href='http://www.baidu.com'>百度</a>");
		
		MailUtils.send(session, mail);
		
	}
	 

}
