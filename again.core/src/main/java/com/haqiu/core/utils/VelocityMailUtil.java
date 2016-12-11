package com.haqiu.core.utils;

import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;


public class VelocityMailUtil {

	private Properties mailHeaders = new Properties();

	/**
	 * 邮件发送者，包括发送者姓名和地址，用于设置在邮件的from栏目中
	 */
	private String from;

	/**
	 * 邮件主题
	 */
	private String subject;

	/**
	 * 邮件内容模板地址/名称
	 */
	private String templateName;

	/**
	 * velocity引擎
	 */
	private VelocityEngine velocityEngine;

	/**
	 * JavMail发送器,通过Spring注入
	 */
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * 使用提供的数据，套入velocity模板，生成最后文本信息。
	 * 
	 * 大部分情况下，这个信息应该就是邮件的内容。
	 */
	public String renderText(Map model) throws VelocityException {
		// 防止乱码，需设置为GBK
		return VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(), getTemplateName(),
				"UTF-8", model);
	}

	public Properties getMailHeaders() {
		return mailHeaders;
	}

	/**
	 * 设置非空mime hader，一般可以利用此设置contentType等
	 * 
	 * @param mailHeaders
	 */
	public void setMailHeaders(Properties mailHeaders) {
		this.mailHeaders = mailHeaders;
	}

	public String getFrom() {
		return this.from;
	}

	public Address getFromAddress() throws AddressException {
		return new InternetAddress(this.from);
	}

	/**
	 * 设置邮件发送者，包括发送者姓名和地址，用于设置在邮件的from栏目中
	 * 
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplateName() {
		return templateName;
	}

	/**
	 * 设置邮件内容模板地址/名称
	 * 
	 * @param templateName
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/**
	 * 获取下层配置的mail发送器，发送邮件, 该mail发送器必须是JavaMailSender，否则抛出CaseException。
	 * 
	 * @return
	 */
	public JavaMailSender getJavaMailSender() {
		return (JavaMailSender) mailSender;
	}

	/**
	 * 设置mail发送器
	 * 
	 * @param mailEngine
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 
	 * 发送Mime邮件简易方法。
	 * 
	 * 以Mime的方式发送邮件，这主要是为能够支持发送html或类似功能（非简单文本）的邮件
	 * 
	 * @param nameOfTo
	 *            邮件接收收人姓名 或 昵称
	 * @param emailOfTo
	 *            邮件接收人邮件地址
	 * @param model
	 *            邮件velocity模板中变量的值
	 * @throws org.springframework.mail.MailException
	 */
	public void sendMime(String nameOfTo, String emailOfTo, Map<String, Object> model)
			throws MailException {
		sendMime(nameOfTo + "<" + emailOfTo + ">", model);
	}

	/**
	 * 发送Mime邮件简易方法。
	 * 
	 * 以Mime的方式发送邮件，这主要是为能够支持发送html或类似功能（非简单文本）的邮件
	 * 
	 * @param to
	 *            邮件接收人邮件地址以及可能的收件人姓名或昵称
	 * @param model
	 *            邮件velocity模板中变量的值
	 */
	public void sendMime(String to, Map<String, Object> model) throws MailException {
		sendMime(mergeSimpleMessage(to, model));
	}

	/**
	 * 发送Mime邮件简易方法。
	 * 
	 * 以Mime的方式发送SimpleMailMessage，这主要是为能够支持发送html或类似功能（非简单文本）的邮件
	 * 
	 * @param simpleMessage
	 * @throws org.springframework.mail.MailException
	 */
	public void sendMime(SimpleMailMessage simpleMessage) throws MailException {
		mailSender.send(toMimeMessage(simpleMessage));
	}

	/**
	 * 产生简单信息
	 * 
	 * @param to
	 * @param model
	 * @return
	 */
	public SimpleMailMessage mergeSimpleMessage(String to, Map<String, Object> model) {
		// render text of mail from velocity template with the data
		String text = null;
		try {
			text = renderText(model);
		} catch (VelocityException e) {
			e.printStackTrace();
		}

		// mail message setting
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(getSubject());
		message.setFrom(getFrom());
		message.setTo(to);
		message.setText(text);
		return message;
	}

	/**
	 * 产生html文本信息
	 * 
	 * @param simpleMailMessage
	 * @return
	 */
	public MimeMessagePreparator toMimeMessage(final SimpleMailMessage simpleMailMessage) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "GBK");
				message.setTo(simpleMailMessage.getTo());
				message.setSubject(simpleMailMessage.getSubject());
				message.setText(simpleMailMessage.getText(), true);
				message.setFrom(simpleMailMessage.getFrom());
			}

		};

		return preparator;
	}

}
