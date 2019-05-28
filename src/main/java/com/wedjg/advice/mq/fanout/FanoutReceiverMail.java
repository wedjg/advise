package com.wedjg.advice.mq.fanout;

import com.wedjg.advice.dto.MailDto;
import com.wedjg.advice.service.MailService;
import com.wedjg.advice.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;

/**
 * 邮件订阅消息接受器
 * @author wedjg
 * @date 2019-05-22
 */
@Component
@RabbitListener(queues = "fanout.mail")
public class FanoutReceiverMail {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private MailService mailService;

    @RabbitHandler
    public void process(String message) {
		logger.info("接收到消息：" + message);
        System.out.println("fanout Receiver Mail  : " + message);
    }

	@RabbitHandler
	public void process(MailDto mailDto) {
    	logger.info("接收到消息：" + mailDto.toString());
		this.sendMail(mailDto);
	}

	/**
	 * 发送邮件
	 * @param mailDto
	 */
	private void sendMail(MailDto mailDto) {

    	//若htmlFileName为空则发送简单邮件
    	if (mailDto.getHtmlFileName() == null || "".equals(mailDto.getHtmlFileName())){
			mailService.sendSimpleMail(mailDto);
			return;
		}

    	//若htmlFileName不为空则发送html邮件
		Context context = new Context();
		//将邮件参数放进context中
		mailDto.getParameter().forEach((k, v)-> context.setVariable(k, v));

		mailDto.setContent(MailUtil.getHtml(mailDto.getHtmlFileName(), context));
		System.out.println(mailDto.getContent());
		mailService.sendHtmlMail(mailDto);
	}
}
