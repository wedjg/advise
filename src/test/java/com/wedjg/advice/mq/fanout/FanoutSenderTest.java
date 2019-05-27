package com.wedjg.advice.mq.fanout;

import com.wedjg.advice.dto.MailDto;
import com.wedjg.advice.util.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    //@Test
    public void send() {
        //sender.send();
    }

    @Test
    public void sendMail() {
        MailDto mail = new MailDto();
        mail.setTo("125490772@qq.com");
        mail.setSubject("打招呼");
        mail.setHtmlFileName("emailTemplate");
        Map map = new HashMap(2);
        map.put("username", "wedjg");
        map.put("time", LocalDateTime.now().toString());
        mail.setParameter(map);

        sender.sendMail(mail);
    }
}