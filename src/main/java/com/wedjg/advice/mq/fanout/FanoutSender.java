package com.wedjg.advice.mq.fanout;

import com.wedjg.advice.dto.MailDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 订阅消息发送-测试
 * @author wedjg
 * @date 2019-05-22
 */
@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context ="hi, fanout msg!" + LocalDateTime.now();
        System.out.println("Sender : " + context);
        AmqpTemplate a = rabbitTemplate;
        this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
    }

    public void sendMail(MailDto mail) {
        System.out.println("Sender : " + mail.toString());
        this.rabbitTemplate.convertAndSend("fanoutExchange","", mail);
    }
}
