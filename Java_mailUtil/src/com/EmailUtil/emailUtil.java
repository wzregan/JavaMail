package com.EmailUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class emailUtil {
	private Properties pro;
	private Message meg;
	public static String PROTOCOL_SMTP="smtp";
	public static String COM163="smtp.163.com";
	private Transport trans;
	private Session session;
	private String username;
	private String passwd;
	private emailUtil(String protocolname,String remote,String name,String pass)
	{
		pro=new Properties();
		pro.put("mail.transport.protocol",protocolname);
		pro.put("mail.smtp.host", remote);
		pro.put("mail.smtp.auth", true);
		pro.put("mail.debug", true);
		meg=new MimeMessage(Session.getInstance(pro));
		session=Session.getInstance(pro);
		session.setDebug(true);
		try {
			
			this.username=name;
			this.passwd=pass;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public static emailUtil getEmailUtil(String protocolname,String remote,String name,String pass)
	{
		
		return new emailUtil(protocolname,remote,name,pass);
	}
	
	public void setMessage(String title,String from,String to,String text)
	{
		try {
			meg.setFrom(new InternetAddress(from));
			meg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			meg.setSubject(title);
			meg.setText(text);
			meg.setSentDate(new Date());
			meg.saveChanges();
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void send()
	{
		try {
			trans=session.getTransport();
			trans.connect(username,passwd);
			trans.sendMessage(meg, meg.getAllRecipients());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}


}
