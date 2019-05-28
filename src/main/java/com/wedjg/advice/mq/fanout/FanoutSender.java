package com.wedjg.advice.mq.fanout;

import com.wedjg.advice.dto.MailDto;
import com.wedjg.advice.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 订阅消息发送-测试
 * @author wedjg
 * @date 2019-05-22
 */
@Component
public class FanoutSender {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context ="hi, fanout msg!" + DateUtil.getStandardTime();
        logger.info("开始发送消息：" + context);
        this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
    }

    public void sendMail(MailDto mail) {
        logger.info("开始发送消息：" + mail.toString());
        this.rabbitTemplate.convertAndSend("fanoutExchange","", mail);
    }
}
