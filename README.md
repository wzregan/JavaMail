# 通过JavaMali API 收发邮件

## E-mail协议简介
&nbsp; &nbsp; &nbsp; 邮件服务器根据服务类型，分为**发送邮件服务器**，和**接收邮件服务器**，现在一般的发送邮件服务器使用**邮件发送协议**，常用的是**SMTP协议**，接收邮件用的协议用的一般是**POP3协议**和**IMAP协议**。	

&nbsp; &nbsp; &nbsp; 客户机A向客户机B发送邮件主要步骤如下：A先把邮件发送到SMTP邮件服务器上，然后通过SMTP协议，把邮件发送到服务器B,服务器B采用POP3协议把邮件发送到客户机B。

>SMTP协议：SMTP全称Simple Mail Transfer Protocol,即简单邮件传输协议，是Internet传送E-mail的基本协议，也是TCP/IP协议的子集。SMTP协议发送一封邮件的过程为，发送邮件之前发送邮件服务器会与接收邮件服务器联系，确定接收服务器是否准备好接收邮件。如果准备好，就发送，如果没有，就等待，一段时间后继续与接收放进行联系。SMTP服务器的端口一般是25。

-----------
>POP3协议：全称Post Office Protocol 3,即邮件协议第三版，也是TCP/IP的子集,POP3服务器既允许接收服务器向邮件用户发送邮件，也允许接收来自SMTP服务器的邮件。POP3服务器的端口一般是110

-------------
>IMAP协议：全称为Internet Message Access Protocol,即互联网消息访问协议，功能比POP3更加强大。

---------------------
>MIME简介：不是邮件传输协议，而是对邮件格式所做的规范。

## 通过JAVA实现---步骤
	一.设置配置Properties
		 1. 属性：mail.transport.protocol 
			作用：指邮件发送使用的协议
			数值: 一般都是使用SMTP
		 2. 属性：mail.smtp.host
		    作用：指发送邮件服务器的IP地址或者主机名
			数值：可以填写自己的邮件服务器，也可以写163的服务器，也可以写sina的服务器
		 3. 属性：mail.smtp.auth
			作用：是否需要权限
			数值：一般都是true
		 4. 属性：mail.debug
			作用：是否在发送邮件时打印过程信息
			数值：一般都是true
	二.创建程序到邮件服务器的一次会话
		Session.getInstance(properties)
	三、创建一封信
		1.创建
			Message msg=new MimeMessage(session);
		2.设置From
			msg.setFrom(new InternetAddress("xxx"));
		3.设置To
			msg.setRecipients(RecipientType.to,InternetAddress[]);
		（可以群发，因为第二参数是一个数组，也可以）
		4.设置标题
			msg.setsubject("xxxx");
		5.设置正文
			msg.setText("这是邮局的正文");
	四、发送邮件
		1.创建邮递员
			Transport trans=session.getTransport();
		2.连接服务器
			Trans.connect(username,passwd);
		3.发送邮件
			Trans.sendMessage(msg,Address);