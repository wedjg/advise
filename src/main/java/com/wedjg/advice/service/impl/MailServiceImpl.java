package com.wedjg.advice.service.impl;

import com.wedjg.advice.dto.MailDto;
import com.wedjg.advice.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * 	邮件服务实现类
 *	@author wedjg
 *	@since 2019/05/16
 */
@Component
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.fromMail.addr}")
    private String from;

    private Properties properties;

	/**
	 *	发送简单邮件
	 */
    @Override
	public void sendSimpleMail(MailDto mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.from);
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());


        try {
            mailSender.send(message);
            logger.info(mail.toString() +"发送成功。");
        } catch (Exception e) {
            logger.error(mail.toString() + "发送异常！", e);
        }

    }
	

	/**
	 * 发送html邮件
	 * @param mail	邮件实体
	 */
	@Override
	public void sendHtmlMail(MailDto mail) {
		sendSeniorMessage(mail);
	}

	/**
	 * 发送带附件的邮件
	 * @param mail	邮件实体
	 */
	@Override
	public void sendAttachmentsMail(MailDto mail){
		sendSeniorMessage(mail);
	}

	/**
	 * 发送带有静态资源的邮件
	 * @param mail	邮件实体
	 */
	@Override
	public void sendInlineResourceMail(MailDto mail){
		sendSeniorMessage(mail);
	}

	/**
	 *	生成通用邮件构造对象
	 * @param message	邮件对象
	 * @param mail	邮件实体
	 * @return
	 * @throws MessagingException
	 */
	private MimeMessageHelper getSeniorMessage(MimeMessage message, MailDto mail)
			throws MessagingException {
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(this.from);
		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getContent(), true);
		return helper;
	}
	
	/**
	 *	发送高级邮件
	 */
	private void sendSeniorMessage(MailDto mail) {
//		properties.put("userName", this.from);
		MimeMessage message = mailSender.createMimeMessage();

		try {
			message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(this.from));
			MimeMessageHelper helper = this.getSeniorMessage(message, mail);
			
			// 发送带附件的邮件
			if(mail.getFilePath() != null){
				String filePath = mail.getFilePath();
				FileSystemResource file = new FileSystemResource(new File(filePath));
				String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
				helper.addAttachment(fileName, file);
			}
			
			//发送带有静态资源的邮件
			if(mail.getRscPath() != null || mail.getRscId() != null) {
				String rscPath = mail.getRscPath();
				String rscId = mail.getRscId();
				FileSystemResource res = new FileSystemResource(new File(rscPath));
				helper.addInline(rscId, res);
			}

			mailSender.send(message);
			logger.info(mail.toString() + "发送成功！");
		} catch (AuthenticationFailedException e) {
			logger.error("身份验证失败：", e);
		} catch (MessagingException e) {
			logger.error(mail.toString() + "发送失败！", e);
		}
	}


}