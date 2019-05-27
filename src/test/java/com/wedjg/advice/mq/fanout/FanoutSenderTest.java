package com.wedjg.advice.mq.fanout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * $
 *
 * @author : Liao Jiajian
 * @date : 2019/05/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutSenderTest {

    @Autowired
    FanoutSender sender;

    @Test
    public void send() {
        //sender.send();
        sender.sendMail();
    }
}