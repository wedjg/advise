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
}
