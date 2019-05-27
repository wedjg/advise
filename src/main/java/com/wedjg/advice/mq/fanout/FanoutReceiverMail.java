package com.wedjg.advice.mq.fanout;

import com.wedjg.advice.dto.MailDto;
import com.wedjg.advice.service.MailService;
import com.wedjg.advice.util.MailUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;

/**
 * 邮件订阅消息接受
 * @author wedjg
 * @date 2019-05-22
 */
@Component
@RabbitListener(queues = "fanout.mail")
public class FanoutReceiverMail {

	@Resource
	private MailService mailService;

    @RabbitHandler
    public void process(String message) {
        System.out.println("fanout Receiver Mail  : " + message);
    }

	@RabbitHandler
	public void process(MailDto mailDto) {
		this.sendMail(mailDto);
	}
	
	public void sendMail(MailDto mailDto) {

		System.out.println("怎么跑到我这里来了？");
		
//		if(mailDto.getHtmlFile() != null) {
//			Context context = new Context();
//			//将邮件参数放进context中
//			mailDto.getParameter().forEach((k, v)-> context.setVariable(k, v));
//
//			mailDto.setContent(MailUtil.getHtml(mailDto.getHtmlFile(), context));
//			mailService.sendHtmlMail(mailDto);
//		}
	}
}
