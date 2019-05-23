package com.wedjg.advice.mq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 邮件订阅消息接受
 * @author wedjg
 * @date 2019-05-22
 */
@Component
@RabbitListener(queues = "fanout.mail")
public class FanoutReceiverMail {

    @RabbitHandler
    public void process(String message) {
        System.out.println("fanout Receiver Mail  : " + message);
    }
	
	public void process(MailDto mailDto) {
		this.sendMail(MailDto mailDto);
	}
	
	public void sendMail(MailDto mailDto) {
		
		if(mailDto.getHtmlFile() != null) {
			Context context = new Context();
			//将参数放进context中
			mailDto.getParameter().forEach((k, v)-> context.setVariable(k, v));
			
			mail.setContent(MailUtil.getHtml(mailDto.getHtmlFile(), context));
			mailService.sendHtmlMail(mail);
		}
	}
}
